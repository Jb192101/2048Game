package org.jedi_bachelor;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jedi_bachelor.viewmodel.MainViewModel;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainViewModel mvm = new MainViewModel();
        mvm.startApp(stage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}