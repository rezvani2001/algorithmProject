package Translator.File;


import Translator.Logger.LogInfo;
import Translator.Translator;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;

/**
 * Translate a whole file using {@link Translator#translate(String)} for each word that is in the file
 */

public class FileTranslator {

    Translator mainRef;


    public FileTranslator(Translator mainRef) {
        this.mainRef = mainRef;
    }

    /**
     * takes a source file path and a dest file path and calls {@link Translator#translate(String)} on each word
     * in source file and stores the result in the dest file
     * @param sourceFilePath source file from which the words will be read
     * @param destinationFilePath dest file to which the translated words will be written to
     */

    public void translate(String sourceFilePath, String destinationFilePath) {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
                File file = new File(destinationFilePath);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                while (!reader.ready()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        mainRef.showAlert("Reading File Attempt Failed, pleaseTryAgain", Alert.AlertType.ERROR);
                        mainRef.log(new LogInfo("sleeping Thread interrupted", e));
                    }
                }
                while (true) {
                    String currentLine = reader.readLine();
                    if (currentLine == null) break;
                    writer.write(mainRef.translate(currentLine));
                }
                reader.close();
                writer.close();

                Platform.runLater(()->{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "file " + sourceFilePath +
                            " \nhas been translated and saved successfully in time of: "+
                            (System.currentTimeMillis() - startTime) + " ms");
                    alert.setTitle("file translated successfully");
                    alert.showAndWait();
                });
            } catch (IOException e) {
                mainRef.showAlert("Reading File Attempt Failed, pleaseTryAgain", Alert.AlertType.ERROR);
                mainRef.log(new LogInfo("Reading File Attempt Failed", e));
            }
        }).start();
    }

}
