package net.eagle.tas.tradersb.ship;

import org.springframework.http.HttpStatus;

public class ShipHasProblemException extends Exception {

    private String msg = "Ship fails validation.";

    public ShipHasProblemException(String msg)
    {
        this.msg = msg;
    }

    public HttpStatus getErrorCode() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public String getHeaderName() {
        return "Error";
    }

    public String getLocalizedMessage() {
        return msg;
    }}
