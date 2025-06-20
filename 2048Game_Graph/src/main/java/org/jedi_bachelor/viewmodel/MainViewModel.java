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
        _stage.show();
    }
}
