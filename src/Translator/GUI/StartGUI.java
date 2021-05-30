package Translator.GUI;

import Translator.LOGIC.LoadTree;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        long loadingTime = LoadTree.loadTree();

        System.out.println(loadingTime);


        new TreeStage().showAndWait();

        System.exit(1);
    }
}
