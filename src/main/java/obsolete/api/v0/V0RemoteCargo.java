package obsolete.api.v0;


import obsolete.api.ApiCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Remote cargo retriever, 11/29/2017.
 * Use this command to figure out what cargo another player is selling.
 */
public class V0RemoteCargo implements ApiCommand {
    public String name() {
        return "remoteCargo";
    }

    public String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap) {
        return "not implemented";
    }
}
