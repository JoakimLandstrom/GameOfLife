package org.gol.model;

import org.gol.model.enums.CellState;

public final class Cell {

    private final int rowNo;
    private final int cellNo;
    private final CellState state;

    public Cell(int rowNo, int cellNo, CellState state) {
        this.rowNo = rowNo;
        this.cellNo = cellNo;
        this.state = state;
    }

    public int getRowNo() {
        return rowNo;
    }

    public int getCellNo() {
        return cellNo;
    }

    public CellState getState() {
        return state;
    }
}
