package Translator;

import Translator.GUI.MainStage;
import Translator.GUI.TreeStage;
import Translator.LOGIC.LoadTree;
import Translator.Logger.LogInfo;
import Translator.Logger.Logger;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.stage.Stage;

import java.util.Date;

//one static OBST object and the translate method
// uses that to translate a string and return its value

public class Translator extends Application {

    private Logger logger;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // start application from here ?
        //INIT
//        primaryStage.close();

        long loadingTime = LoadTree.loadTree();

        logger = new Logger(this);
        //INIT_End
        logger.log(new LogInfo("All BST has been created in " + loadingTime + " ms" , null));

//         new MainStage(this).show();

        new TreeStage().showAndWait();
        //destructor
        logger.closeStream();
        //destructor
    }

    public String translate (String value){
        value.trim();
        if(value.indexOf(' ') != -1){
            //TODO : use StringBulider and translate the whole sentence by splitting
        }else {

        }
        //TODO : finds the translation of a word in the tree
        return "";
    }

    public void showAlert(String message, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args){
        launch(args);
    }

    public void log(LogInfo logInfo){
        logger.log(logInfo);
    }
}
