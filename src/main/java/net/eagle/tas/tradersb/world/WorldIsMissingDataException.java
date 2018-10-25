package net.eagle.tas.tradersb.world;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class WorldIsMissingDataException extends Exception {

    public String getHeaderName() {
        return "error";
    }

    public HttpStatus getErrorCode() {
        return HttpStatus.PARTIAL_CONTENT;
    }

    public WorldIsMissingDataException(HashMap sourceData) {
        super("World is missing data.");
    }
}
