package Translator.Logger;

import java.util.Date;

public class LogInfo {
    String message;
    Exception exception;
    Date date;

    public LogInfo(String message, Exception exception) {
        this.message = message;
        this.exception = exception;
        date = new Date();
    }

    public LogInfo(String message) {
        this(message, null);
    }

    public LogInfo(Exception exception) {
        this("N/A", exception);
    }
}
