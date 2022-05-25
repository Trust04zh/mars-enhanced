# Mars-enhanced

An enhanced version for Mars project.

## Build

This project uses java 8 by default, you can modify `pom.xml` and build your own version.

Manual build (jarfile)
```bash
$ mvn package
```

## Usage

```bash
$ java -jar Mars.jar to_dump.asm a dump .text COE text.coe pl 64K dump .data COE data.coe pl 64K
```

## Roadmap

Work list:

- [x] add CLI support for dumping coe file
- [x] add GUI support for dumping coe file
- [] add drag-and-drop support for opening assembly file(s)
- [] add modern themes (java swing look and feel)
- [] support stopping endless loop

## Other info and special thanks

The original [Mars](http://courses.missouristate.edu/kenvollmar/mars/) project is license by [MIT license](http://www.opensource.org/licenses/mit-license.html), this project keeps it.

Some ideas are inspired by [@frankss](https://github.com/GhostFrankWu/), and [@Fro1ser](https://github.com/Fros1er) for [Fro1ser/MARS_GUI](https://github.com/Fros1er/MARS_GUI)