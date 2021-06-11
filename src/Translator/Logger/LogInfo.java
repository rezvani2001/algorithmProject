package Translator.Logger;

import java.util.Date;

/**
 * Holds the info that needs to be logged and since it just gonna hold them the fields are public
 */

public class LogInfo {
    String message;
    Exception exception;
    Date date;

    public LogInfo(String message, Exception exception) {
        this.message = message;
        this.exception = exception;
        date = new Date();
    }

}