package Translator;

import Translator.Logger.LogInfo;
import Translator.Logger.Logger;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.management.remote.JMXServerErrorException;

//one static OBST object and the translate method
// uses that to translate a string and return its value

public class Translator extends Application {

    private Logger logger;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // start application from here ?
        //INIT
        logger = new Logger(this);
        //INIT_End

        //destructor
        logger.closeStream();
        //destructor
    }

    public String translate (String value){
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