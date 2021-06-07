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
                    //TODO : for sadra : recheck this i cant remember if i wrote this completely or not!
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
                    while (reader.readLine() != null) {
                        String currentLine = reader.readLine();
                        for (String value : currentLine.split(" ")) {
                            writer.write(mainRef.translate(value));
                        }
                        writer.write('\n');
                    }
                } catch (IOException e) {
                    mainRef.showAlert("Reading File Attempt Failed, pleaseTryAgain", Alert.AlertType.ERROR);
                    mainRef.log(new LogInfo("Reading File Attempt Failed", e));
                }
            }
        }).start();
    }

}
