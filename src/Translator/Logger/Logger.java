package Translator.Logger;

import Translator.Translator;
import javafx.scene.control.Alert;

import java.io.*;

public class Logger {
    BufferedWriter fos;
    Translator mainRef;
    private final static String EVENT_BORDER = "================================";

    public Logger(Translator mainReference) {
        this.mainRef = mainReference;
        try {
            fos = new BufferedWriter(new FileWriter("TnsLog.txt"));
        } catch (Exception e) {
            mainRef.showAlert("Couldn't Access Log FIle Please restart", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void log(LogInfo event){
        try {
            fos.write(EVENT_BORDER + "\n");
            fos.write("-" + event.message + "\n");
            fos.write("-" + (event.exception != null ? event.exception.getMessage() : "No Exception") + "\n");
            fos.write("-" + event.date.toString() + "\n");
            fos.write(EVENT_BORDER + "\n");
        } catch (IOException e) {
            mainRef.showAlert("Couldn't Access Log FIle Please restart", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    public void closeStream(){
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
