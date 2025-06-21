package org.jedi_bachelor.viewmodel;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jedi_bachelor.model.Game;
import org.jedi_bachelor.view.MainView;

public class MainViewModel {
    private final Game game;
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

        // Загрузка данных во View из Game
        updateTiles();

        // Обработка нажатий
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> {
                    game.moveUp();
                    updateTiles();
                    break;
                }
                case DOWN -> {
                    game.moveDown();
                    updateTiles();
                    break;
                }
                case LEFT -> {
                    game.moveLeft();
                    updateTiles();
                    break;
                }
                case RIGHT -> {
                    game.moveRight();
                    updateTiles();
                    break;
                }
            }
        });

        _stage.show();

        startGameThread();
    }

    private void updateTiles() {
        int[][] map = game.getMap();
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                mv.updateTile(map[i][j], i, j);
            }
        }
    }

    public void startGameThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(!game.isGameOver()) {

                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
