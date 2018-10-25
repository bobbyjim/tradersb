package net.eagle.tas.tradersb.ship;

import org.springframework.http.HttpStatus;

public class CannotUpdateShipException extends Exception {

    public HttpStatus getErrorCode() {
        return HttpStatus.NOT_FOUND;
    }

    public String getHeaderName() {
        return "Error";
    }

    public String getLocalizedMessage() {
        return "Cannot update: ship not found";
    }
}
