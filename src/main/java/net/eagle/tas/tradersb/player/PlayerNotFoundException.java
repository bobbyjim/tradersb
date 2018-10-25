package net.eagle.tas.tradersb.player;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends Exception {

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
