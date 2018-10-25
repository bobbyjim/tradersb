package obsolete.api;

import obsolete.api.v0.*;

public class APIv0CommandFactory implements API {
    private ApiCommand person = new V0Person();
    private ApiCommand xmail = new V0Xmail();
    private ApiCommand remotePlayer = new V0RemotePlayer();
    private ApiCommand remoteShip = new V0RemoteShip();
    private ApiCommand remoteCargo = new V0RemoteCargo();
    private ApiCommand jump = new V0Jump();
    private ApiCommand ship = new V0Ship();
    private ApiCommand doNothing = new V0DoNothing();

    public ApiCommand getDefaultCommand() {
        return doNothing;
    }

    public ApiCommand getCommand(String stemNoun) {
        String cmd = stemNoun.toLowerCase();
        switch (cmd) {
            case "person":
                return person;
            case "xmail":
                return xmail;
            case "remotePlayer":
                return remotePlayer;
            case "remoteShip":
                return remoteShip;
            case "remoteCargo":
                return remoteCargo;
            case "jump":
                return jump;
            case "ship":
                return ship;
            default:
                return doNothing;
        }
    }
}
