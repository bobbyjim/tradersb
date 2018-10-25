package net.eagle.tas.tradersb.ship;

import org.springframework.http.HttpStatus;

public class ShipNotFoundException extends Exception {

    public HttpStatus getErrorCode() {
        return HttpStatus.NOT_FOUND;
    }

    public String getHeaderName() {
        return "Error";
    }

    public String getLocalizedMessage() {
        return "Ship not found";
    }
}
