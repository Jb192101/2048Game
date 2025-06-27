package org.jedi_bachelor.viewmodel;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jedi_bachelor.model.Game;
import org.jedi_bachelor.view.MainView;

public class MainViewModel {
    private Game game;
    private MainView mv;

    public MainViewModel() {
        this.game = new Game();
        this.mv = new MainView();
    }

    public void startApp(Stage _stage) {
        Scene scene = new Scene(mv);
        _stage.setTitle("2048 - Графика");
        _stage.setScene(scene);
        _stage.setResizable(false);

        updateTiles();

        scene.setOnKeyPressed(e -> {
            boolean moved = false;

            switch (e.getCode()) {
                case UP -> {
                    game.moveUp();
                    moved = true;
                }
                case DOWN -> {
                    game.moveDown();
                    moved = true;
                }
                case LEFT -> {
                    game.moveLeft();
                    moved = true;
                }
                case RIGHT -> {
                    game.moveRight();
                    moved = true;
                }
            }

            if (moved) {
                updateTiles();
                if (game.isGameOver()) {
                    showGameOver();
                }
            }
        });

        _stage.show();
    }

    private void updateTiles() {
        Platform.runLater(() -> {
            int[][] map = game.getMap();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    mv.updateTile(map[i][j], i, j);
                }
            }
        });
    }

    private void showGameOver() {
        Platform.runLater(() -> {
            mv.showGameOverDialog(game.getScore(), this::restartGame);
        });
    }

    private void restartGame() {
        this.game = new Game();
        updateTiles();
    }
}
