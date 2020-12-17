# Game Of Life

The Game of Life is an example of a cellular automaton and a zero-player game.

## Installation

Java 15

## Installation

Use the wrapper to build and run the tests.

On Windows:

```bash
mvnw.cmd clean install
```

On Unix:

```bash
./mvnw clean install
```

A jar is in then created in the target folder.

To run that jar:

```bash
java -jar GameOfLife-1.0.jar
```

The game will stop itself if no live cells are on the board. 
Otherwise it will keep running.
