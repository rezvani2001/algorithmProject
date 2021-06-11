package Translator.Logger;

import Translator.Translator;
import javafx.scene.control.Alert;

import java.io.*;

/**
 * Logs the values of given objects of {@link LogInfo} to a logFile named "TnsLog.txt"
 */

public class Logger {
    FileWriter fos;
    Translator mainRef;
    private final static String EVENT_BORDER = "================================";

    /**
     * inits the fileWriter and takes a reference to main to be able to show alerts
     * @param mainReference reference to class containing main which is {@link Translator}
     */

    public Logger(Translator mainReference) {
        this.mainRef = mainReference;
        try {
            fos = new FileWriter("TnsLog.txt", true);
        } catch (Exception e) {
            mainRef.showAlert("Couldn't Access Log File Please restart", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * writes the content of a {@link LogInfo} obj and adds borders to its text
     * @param info obj the values of which is to be written to file
     */

    public void log(LogInfo info){
        try {
            fos.write(EVENT_BORDER + "\n");
            fos.write("-" + info.message + "\n");
            fos.write("-" + (info.exception != null ? info.exception.getMessage() : "No Exception") + "\n");
            fos.write("-" + info.date.toString() + "\n");
            fos.write(EVENT_BORDER + "\n");
        } catch (IOException e) {
            mainRef.showAlert("Couldn't Access Log FIle Please restart", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    /**
     * closes the file stream since garbage collector might not so it must be used when the obj
     * is not to be used any more
     */

    public void closeStream(){
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
