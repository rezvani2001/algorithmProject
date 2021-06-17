package Translator;

import Translator.File.FileTranslator;
import Translator.GUI.MainStage;
import Translator.LOGIC.LoadTree;
import Translator.Logger.LogInfo;
import Translator.Logger.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

// one static OBST object and the translate method
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
        stage.setOnCloseRequest(event -> logger.closeStream());
        //destructor
    }

    /**
     * takes in a string and removes untranslatable values from it and then translates using {@link LoadTree}
     * it and returns it
     * @param value the string that will be translated
     * @return farsi translation of the given english string
     */

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

            for (String word : words) {
                if (!isWord(word)) {
                    result.append(word).append(' ');
                } else {
                    result.append(LoadTree.search(word)).append(' ');
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

    /**
     * instantiates one obj of class {@link FileTranslator} and class its translate method with the given file path
     * @param filePath path of the file that will be translated
     */

    public void translateFile(String filePath){
        FileTranslator fT = new FileTranslator(this);
        fT.translate(filePath , filePath + "\\..\\(translated).txt");
    }

    /**
     * shows and alert to the user
     * @param message message to be in context section of the alert
     * @param alertType the alertType property of the Alert
     */

    public void showAlert(String message, Alert.AlertType alertType) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setContentText(message);
            alert.showAndWait();
        });

    }

    /**
     * checks if the given string has other values than just english letters
     * @param str value to be checked if it is an english word or not
     * @return true if only contains eng letters, false otherwise
     */
    private boolean isWord(String str){
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) > 122 || str.charAt(i) < 97) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * uses {@link Translator#logger} to log an obj of {@link LogInfo} to file
     * @param logInfo the obj that the content of which will be logged
     */

    public void log(LogInfo logInfo) {
        logger.log(logInfo);
    }
}
