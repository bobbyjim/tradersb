package net.eagle.tas.tradersb.player;

import net.eagle.tas.tradersb.exception.ExceptionResponse;
import net.eagle.tas.tradersb.exception.TraderException;
import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends TraderException {

    public HttpStatus getErrorCode() {
        return HttpStatus.NOT_FOUND;
    }

    public String getHeaderName() {
        return "error";
    }

    public String getLocalizedMessage() {
        return "Player not found";
    }
}
