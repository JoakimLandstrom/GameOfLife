import org.gol.model.Cell;
import org.gol.model.enums.CellState;
import org.gol.presenter.GameOfLifePresenter;
import org.gol.view.GameOfLifeView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifePresenterTests {

    private static final GameOfLifePresenter presenter = new GameOfLifePresenter(new GameOfLifeView(10, 10));

    @Test
    public void getNewCellValue_LiveAndThreeLiveNeighbours_ReturnsOne() {

        var aliveNeighbours = 3;
        var cellValue = CellState.ALIVE;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.ALIVE, actual);
    }

    @Test
    public void getNewCellValue_LiveAndTwoLiveNeighbours_ReturnsOne() {
        var aliveNeighbours = 2;
        var cellValue = CellState.ALIVE;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.ALIVE, actual);
    }

    @Test
    public void getNewCellValue_LiveAndOneLiveNeighbours_ReturnsZero() {
        var aliveNeighbours = 1;
        var cellValue = CellState.ALIVE;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.DEAD, actual);
    }

    @Test
    public void getNewCellValue_LiveAndFourLiveNeighbours_ReturnsZero() {
        var aliveNeighbours = 4;
        var cellValue = CellState.ALIVE;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.DEAD, actual);
    }

    @Test
    public void getNewCellValue_DeadAndThreeLiveNeighbours_ReturnsOne() {
        var aliveNeighbours = 3;
        var cellValue = CellState.DEAD;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.ALIVE, actual);
    }

    @Test
    public void getNewCellValue_DeadAndTwoLiveNeighbours_ReturnsZero() {
        var aliveNeighbours = 2;
        var cellValue = CellState.DEAD;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.DEAD, actual);
    }

    @Test
    public void getNewCellValue_DeadAndFourLiveNeighbours_ReturnsZero() {
        var aliveNeighbours = 4;
        var cellValue = CellState.DEAD;

        var actual = presenter.getNewCellValue(cellValue, aliveNeighbours);

        assertEquals(CellState.DEAD, actual);
    }

    @Test
    public void countAliveNeighbours_AliveAndTwoNeighbours_ReturnsTwo() {

        /*
        . # .
        . # .
        . # .
         */
        var board = new Cell[3][3];
        board[0][0] = new Cell(0, 0, CellState.DEAD);
        board[0][1] = new Cell(0, 1, CellState.ALIVE);
        board[0][2] = new Cell(0, 2, CellState.DEAD);
        board[1][0] = new Cell(1, 0, CellState.DEAD);
        board[1][1] = new Cell(1, 1, CellState.ALIVE);
        board[1][2] = new Cell(1, 2, CellState.DEAD);
        board[2][0] = new Cell(2, 0, CellState.DEAD);
        board[2][1] = new Cell(2, 1, CellState.ALIVE);
        board[2][2] = new Cell(2, 2, CellState.DEAD);

        var count = presenter.countAliveNeighbours(board[1][1], board);

        assertEquals(2, count);
    }

    @Test
    public void countAliveNeighbours_AliveAndNoNeighbour_ReturnsZero() {

        /*
        . # .
        . . .
        . . .
         */
        var board = new Cell[3][3];
        board[0][0] = new Cell(0, 0, CellState.DEAD);
        board[0][1] = new Cell(0, 1, CellState.ALIVE);
        board[0][2] = new Cell(0, 2, CellState.DEAD);
        board[1][0] = new Cell(1, 0, CellState.DEAD);
        board[1][1] = new Cell(1, 1, CellState.DEAD);
        board[1][2] = new Cell(1, 2, CellState.DEAD);
        board[2][0] = new Cell(2, 0, CellState.DEAD);
        board[2][1] = new Cell(2, 1, CellState.DEAD);
        board[2][2] = new Cell(2, 2, CellState.DEAD);

        var count = presenter.countAliveNeighbours(board[0][1], board);

        assertEquals(0, count);
    }

    @Test
    public void countAliveNeighbours_DeadAndThreeNeighbours_ReturnsThree() {

        /*
        . # .
        . # .
        . # .
         */
        var board = new Cell[3][3];
        board[0][0] = new Cell(0, 0, CellState.DEAD);
        board[0][1] = new Cell(0, 1, CellState.ALIVE);
        board[0][2] = new Cell(0, 2, CellState.DEAD);
        board[1][0] = new Cell(1, 0, CellState.DEAD);
        board[1][1] = new Cell(1, 1, CellState.ALIVE);
        board[1][2] = new Cell(1, 2, CellState.DEAD);
        board[2][0] = new Cell(2, 0, CellState.DEAD);
        board[2][1] = new Cell(2, 1, CellState.ALIVE);
        board[2][2] = new Cell(2, 2, CellState.DEAD);

        var count = presenter.countAliveNeighbours(board[1][0], board);

        assertEquals(3, count);
    }


    @Test
    public void anyCellsALive_OneAlive_ReturnsTrue() {

        var board = new Cell[3][3];
        board[0][0] = new Cell(0, 0, CellState.DEAD);
        board[0][1] = new Cell(0, 1, CellState.ALIVE);
        board[0][2] = new Cell(0, 2, CellState.DEAD);
        board[1][0] = new Cell(1, 0, CellState.DEAD);
        board[1][1] = new Cell(1, 1, CellState.DEAD);
        board[1][2] = new Cell(1, 2, CellState.DEAD);
        board[2][0] = new Cell(2, 0, CellState.DEAD);
        board[2][1] = new Cell(2, 1, CellState.DEAD);
        board[2][2] = new Cell(2, 2, CellState.DEAD);

        var result = presenter.anyCellsAlive(board);

        assertTrue(result);
    }

    @Test
    public void anyCellsALive_TwoAlive_ReturnsTrue() {

        var board = new Cell[3][3];
        board[0][0] = new Cell(0, 0, CellState.DEAD);
        board[0][1] = new Cell(0, 1, CellState.ALIVE);
        board[0][2] = new Cell(0, 2, CellState.ALIVE);
        board[1][0] = new Cell(1, 0, CellState.DEAD);
        board[1][1] = new Cell(1, 1, CellState.DEAD);
        board[1][2] = new Cell(1, 2, CellState.DEAD);
        board[2][0] = new Cell(2, 0, CellState.DEAD);
        board[2][1] = new Cell(2, 1, CellState.DEAD);
        board[2][2] = new Cell(2, 2, CellState.DEAD);

        var result = presenter.anyCellsAlive(board);

        assertTrue(result);
    }

    @Test
    public void anyCellsALive_ZeroAlive_ReturnsFalse() {

        var board = new Cell[3][3];
        board[0][0] = new Cell(0, 0, CellState.DEAD);
        board[0][1] = new Cell(0, 1, CellState.DEAD);
        board[0][2] = new Cell(0, 2, CellState.DEAD);
        board[1][0] = new Cell(1, 0, CellState.DEAD);
        board[1][1] = new Cell(1, 1, CellState.DEAD);
        board[1][2] = new Cell(1, 2, CellState.DEAD);
        board[2][0] = new Cell(2, 0, CellState.DEAD);
        board[2][1] = new Cell(2, 1, CellState.DEAD);
        board[2][2] = new Cell(2, 2, CellState.DEAD);

        var result = presenter.anyCellsAlive(board);

        assertFalse(result);
    }

}
