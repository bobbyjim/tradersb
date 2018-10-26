package net.eagle.tas.tradersb.ship;

import net.eagle.tas.tradersb.exception.ExceptionResponse;
import net.eagle.tas.tradersb.exception.TraderException;
import org.springframework.http.HttpStatus;

public class CannotUpdateShipException extends TraderException {

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
