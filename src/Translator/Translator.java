package Translator;

import Translator.File.FileTranslator;
import Translator.GUI.MainStage;
import Translator.GUI.TreeStage;
import Translator.LOGIC.LoadTree;
import Translator.Logger.LogInfo;
import Translator.Logger.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.stage.Stage;

import java.util.Arrays;
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
        logger.log(new LogInfo("All BST has been created in " + loadingTime + " ms", null));


        MainStage stage = new MainStage(this);
        stage.showAndWait();

        //destructor
        logger.closeStream();
        stage.setOnCloseRequest(event ->{
            logger.closeStream();
        });
        //destructor
    }

    public String translate(String value) {
        long time = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        value = value.replaceAll("\n" , " ");
        value = value.replaceAll("\t" , " ");
        value = value.trim();
        value = value.toLowerCase();
        if (value.indexOf(' ') == -1) {
            result.append(LoadTree.search(value)).append("\n");
        } else {
            String[] words = value.split(" ");

            for (int j = 0; j < words.length; j++) {
                if(!isWord(words[j])){
                    result.append(words[j]).append(' ');
                }else{
                    result.append(LoadTree.search(words[j])).append(' ');
                }

            }

            result.append("\n");
        }

        this.logger.log(new LogInfo("Translation has been completed\n\n" +
                "Word: " + value + "\n" +
                "Search Time: " + (System.currentTimeMillis() - time) + "\n" +
                "Translated To: " + result.toString() , null));
        return result.toString();
    }

    public void translateFile(String filePath){
        FileTranslator fT = new FileTranslator(this);
        fT.translate(filePath,(filePath.split("\\.")[0] + "(translated).txt"));
    }

    public void showAlert(String message, Alert.AlertType alertType) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(alertType);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });

    }

    private boolean isWord(String str){
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) > 122 || str.charAt(i) < 97) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void log(LogInfo logInfo) {
        logger.log(logInfo);
    }
}
