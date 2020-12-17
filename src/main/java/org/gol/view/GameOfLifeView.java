package org.gol.view;

import org.gol.model.Cell;
import org.gol.model.enums.CellState;

public final class GameOfLifeView implements IGameOfLifeView {

    private final int width;
    private final int height;

    public GameOfLifeView(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void printGeneration(Cell[][] board) {

        var builder = new StringBuilder();
        builder.append("\n");
        for (Cell[] row : board) {

            for (Cell cell : row)
                builder.append(cell.getState() == CellState.DEAD ? " ." : " #");

            builder.append("\n");
        }

        builder.append("-".repeat(Math.max(0, this.getWidth() * 2)));

        System.out.print(builder.toString());
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
