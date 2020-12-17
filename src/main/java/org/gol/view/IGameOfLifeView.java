package org.gol.view;

import org.gol.model.Cell;

public interface IGameOfLifeView {

    void printGeneration(Cell[][] board);

    int getWidth();

    int getHeight();
}
