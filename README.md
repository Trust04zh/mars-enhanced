An example to dump both .text and .data segment into coe files

```bash
$ java -jar Mars.jar to_dump.asm a dump .text COE text.coe pl 64K dump .data COE data.coe pl 64K
```

An enhanced version for Mars project.

Work list:

- [x] add CLI support for dumping coe file
- [] add GUI support for dumping coe file
- [] add drag-and-drop support for opening assembly file(s)
- [] add modern themes (java swing look and feel)
- [] support stopping endless loop

The original [Mars](http://courses.missouristate.edu/kenvollmar/mars/) project is license by [MIT license](http://www.opensource.org/licenses/mit-license.html), this project keeps it.

some ideas are inspired by [@frankss](https://github.com/GhostFrankWu/), and [@Fro1ser](https://github.com/Fros1er) for [Fro1ser/MARS_GUI](https://github.com/Fros1er/MARS_GUI)