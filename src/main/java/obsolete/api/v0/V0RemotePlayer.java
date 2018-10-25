package obsolete.api.v0;

import obsolete.api.ApiCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Remote player retriever, 11/29/2017.
 * Use this command to get the status of another player.
 */
public class V0RemotePlayer implements ApiCommand {
    public String name() {
        return "remotePlayer";
    }

    public String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap) {
        return "not implemented";
    }
}
