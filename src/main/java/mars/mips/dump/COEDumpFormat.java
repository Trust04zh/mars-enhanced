package mars.mips.dump;

import mars.Globals;
import mars.mips.hardware.*;
import java.io.*;
/*
Copyright (c) 2022,  Trust_04zh

Developed by Trust_04zh (trust04zh@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining 
a copy of this software and associated documentation files (the 
"Software"), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, 
distribute, sublicense, and/or sell copies of the Software, and to 
permit persons to whom the Software is furnished to do so, subject 
to the following conditions:

The above copyright notice and this permission notice shall be 
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR 
ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

(MIT license, http://www.opensource.org/licenses/mit-license.html)
 */

/**
 * Class that represents the hex format.
 * The output initialization vector uses radix 16 (hexadecimal).
 * @author Trust_04zh
 * @version May 2022
 */


public class COEDumpFormat extends AbstractDumpFormat {

    private final String MEMORY_INITIALIZATION_RADIX_HEX_IMPLEMENTATION = "memory_initialization_radix=16;";
    private final String MEMORY_INITIALIZATION_VECTOR_PREFIX = "memory_initialization_vector=";
    private final String MEMORY_PADDING_ZERO = "00000000";
    private final int DEFAULT_VECTOR_LENGTH = 1 << 16;

    /**
     *  Constructor.  File extention is "coe".
     */
    public COEDumpFormat() {
        super("COE Format", "COE", "Written as hex stream to coe file", "coe");
    }


    /**
     *  Write MIPS memory contents in coe format.  The radix is hard encoded to be 16,
     *  so it's quite like a hexadecimal text format but the output follows the format
     *  of coe file, see https://docs.xilinx.com/r/2021.2-English/ug896-vivado-ip/COE-File-Syntax
     *  for the detailed format.
     *  using PrintStream's println() method.
     *  Adapted by Trust_04zh from code written by Pete Sanderson and Greg Gibeling.
     *
     *  @param  file  File in which to store MIPS memory contents.
     *  @param firstAddress first (lowest) memory address to dump.  In bytes but
     *  must be on word boundary.
     *  @param lastAddress last (highest) memory address to dump.  In bytes but
     *  must be on word boundary.  Will dump the word that starts at this address.
     *  @param paddingToLength padding zero to dumped data to specified byte length.
     *  @throws AddressErrorException if firstAddress is invalid or not on a word boundary.
     *  @throws IOException if error occurs during file output.
     */
    public void dumpMemoryRange(File file, int firstAddress, int lastAddress, int paddingToLength)
            throws AddressErrorException, IOException {
        PrintStream out = new PrintStream(new FileOutputStream(file));
        try {
            out.println(MEMORY_INITIALIZATION_RADIX_HEX_IMPLEMENTATION);
            out.println(MEMORY_INITIALIZATION_VECTOR_PREFIX);
            int VectorLength = (paddingToLength == -1) ? DEFAULT_VECTOR_LENGTH : paddingToLength;
            int address = firstAddress;
            while (address <= lastAddress && address - firstAddress < VectorLength) {
                Integer temp = Globals.memory.getRawWordOrNull(address);
                if (temp == null)
                    break;
                StringBuilder sb = new StringBuilder(Integer.toHexString(temp));
                while (sb.length() < 8) {
                    sb.insert(0, '0');
                }
                address += Memory.WORD_LENGTH_BYTES;
                sb.append((address - firstAddress == VectorLength) ? ';' : ',');
                out.println(sb.toString());
            }
            while (address - firstAddress < VectorLength) {
                StringBuilder sb = new StringBuilder(MEMORY_PADDING_ZERO);
                address += Memory.WORD_LENGTH_BYTES;
                sb.append((address - firstAddress == VectorLength) ? ';' : ',');
                out.println(sb.toString());
            }
        }
        finally {
            out.close();
        }
    }

}