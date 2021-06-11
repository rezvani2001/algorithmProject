package Translator.File;


import Translator.Logger.LogInfo;
import Translator.Translator;
import javafx.scene.control.Alert;

import java.io.*;

public class FileTranslator {

    Translator mainRef;

    public FileTranslator(Translator mainRef) {
        this.mainRef = mainRef;
    }

    public void translate(String sourceFilePath, String destinationFilePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));
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
                } catch (IOException e) {
                    mainRef.showAlert("Reading File Attempt Failed, pleaseTryAgain", Alert.AlertType.ERROR);
                    mainRef.log(new LogInfo("Reading File Attempt Failed", e));
                }
            }
        }).start();
    }

}
