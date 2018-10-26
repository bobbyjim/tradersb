package net.eagle.tas.tradersb.world;

import net.eagle.tas.tradersb.exception.TraderException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class WorldIsMissingDataException extends TraderException {

    public HttpStatus getErrorCode() {
        return HttpStatus.PARTIAL_CONTENT;
    }

    public String getHeaderName() {
        return "Error";
    }

    public String getLocalizedMessage() {
        return "World is missing data.";
    }

    /*
    public WorldIsMissingDataException(HashMap sourceData) {
        super("World is missing data.");
    }
    */
}
