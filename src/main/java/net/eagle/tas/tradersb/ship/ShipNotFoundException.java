package net.eagle.tas.tradersb.ship;

import net.eagle.tas.tradersb.exception.ExceptionResponse;
import net.eagle.tas.tradersb.exception.TraderException;
import org.springframework.http.HttpStatus;

public class ShipNotFoundException extends TraderException {

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
