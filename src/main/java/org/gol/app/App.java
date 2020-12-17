package org.gol.app;

import org.gol.presenter.GameOfLifePresenter;
import org.gol.view.GameOfLifeView;

public class App {

    public static void main(String[] args) throws InterruptedException {

        var game = new GameOfLifePresenter(new GameOfLifeView(20, 40));

        game.run();
    }
}
