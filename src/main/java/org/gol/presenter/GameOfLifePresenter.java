package org.gol.presenter;

import org.gol.model.Cell;
import org.gol.model.enums.CellState;
import org.gol.view.IGameOfLifeView;

import java.util.Arrays;
import java.util.Random;

public final class GameOfLifePresenter {

    private final IGameOfLifeView view;
    private Cell[][] currentBoard;

    public GameOfLifePresenter(IGameOfLifeView view) {
        this.view = view;

        currentBoard = createEmptyBoard();

        var random = new Random();

        for (var rowNo = 0; rowNo < view.getHeight(); rowNo++) {
            for (var cellNo = 0; cellNo < view.getWidth(); cellNo++) {

                var state = CellState.values()[random.nextInt(2) % 2];

                currentBoard[rowNo][cellNo] = new Cell(rowNo, cellNo, state);
            }
        }
    }

    public void run() throws InterruptedException {

        var anyCellsAlive = true;

        while (anyCellsAlive) {

            view.printGeneration(currentBoard);

            anyCellsAlive = anyCellsAlive(currentBoard);

            currentBoard = getNextGenerationBoard(currentBoard);

            Thread.sleep(1500);
        }
    }

    public CellState getNewCellValue(CellState oldState, int aliveNeighbours) {

        var result = CellState.DEAD;

        if (oldState == CellState.ALIVE && (aliveNeighbours == 2 || aliveNeighbours == 3))
            result = CellState.ALIVE;
        else if (oldState == CellState.DEAD && aliveNeighbours == 3)
            result = CellState.ALIVE;

        return result;
    }

    public int countAliveNeighbours(Cell cell, Cell[][] board) {

        var cellBounds = view.getWidth() - 1;
        var rowBounds = view.getHeight() - 1;

        var rowMin = cell.getRowNo() - 1;
        var rowMax = cell.getRowNo() + 1;

        var cellMin = cell.getCellNo() - 1;
        var cellMax = cell.getCellNo() + 1;

        var aliveNeighbours = 0;

        for (var rowNo = rowMin; rowNo <= rowMax; rowNo++) {
            for (var cellNo = cellMin; cellNo <= cellMax; cellNo++) {

                if (rowNo > -1 && rowNo <= rowBounds && cellNo > -1 && cellNo <= cellBounds)
                    if (board[rowNo][cellNo].getState() == CellState.ALIVE)
                        aliveNeighbours++;
            }
        }

        //Remove the cell itself as it shouldn't be counted as a neighbour.
        if (board[cell.getRowNo()][cell.getCellNo()].getState() == CellState.ALIVE)
            aliveNeighbours--;

        return aliveNeighbours;
    }

    public boolean anyCellsAlive(Cell[][] board) {
        return Arrays.stream(board).anyMatch(row -> Arrays.stream(row).anyMatch(cell -> cell.getState() == CellState.ALIVE));
    }

    private Cell[][] getNextGenerationBoard(Cell[][] oldBoard) {

        var newBoard = createEmptyBoard();

        for (var rowNo = 0; rowNo < oldBoard.length; rowNo++) {
            for (var cellNo = 0; cellNo < oldBoard[rowNo].length; cellNo++) {

                var oldCell = oldBoard[rowNo][cellNo];
                var oldState = oldCell.getState();

                var aliveNeighbours = countAliveNeighbours(oldCell, oldBoard);

                var newState = getNewCellValue(oldState, aliveNeighbours);

                newBoard[rowNo][cellNo] = new Cell(rowNo, cellNo, newState);
            }
        }

        return newBoard;
    }

    private Cell[][] createEmptyBoard() {

        var newBoard = new Cell[view.getHeight()][view.getWidth()];

        for (var rowNo = 0; rowNo < view.getHeight(); rowNo++)
            for (var cellNo = 0; cellNo < view.getWidth(); cellNo++)
                newBoard[rowNo][cellNo] = new Cell(rowNo, cellNo, CellState.DEAD);

        return newBoard;
    }
}
