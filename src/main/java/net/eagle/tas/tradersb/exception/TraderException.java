package net.eagle.tas.tradersb.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * make this class a standard across your entire organization!
 */

abstract public class TraderException extends Exception {
    Date timestamp;
/*
    String message;
    String details;
    HttpStatus status;
    String headerName;*/

    public TraderException()
    {
        super();
        this.timestamp = new Date();
    }
/*
    public ExceptionResponse(String headername, String message, String details, HttpStatus status) {
        super();
        this.headerName = headername;
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
        this.status = status;
    }
*/
    public Date getTimestamp() {
        return timestamp;
    }

    abstract public String getLocalizedMessage();
    abstract public HttpStatus getErrorCode();
    abstract public String getHeaderName();
}
