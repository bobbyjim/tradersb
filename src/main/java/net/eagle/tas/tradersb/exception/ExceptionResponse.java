package net.eagle.tas.tradersb.exception;

import java.util.Date;

/**
 * make this class a standard across your entire organization!
 */

public class ExceptionResponse {
    Date timestamp;
    String message;
    String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
