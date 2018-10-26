package net.eagle.tas.tradersb.ship;

import net.eagle.tas.tradersb.exception.TraderException;
import org.springframework.http.HttpStatus;

public class ShipHasProblemException extends TraderException {

    private String msg;

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
