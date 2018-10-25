package obsolete.api.v0;

import obsolete.api.ApiCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Xmail retriever, 11/29/2017.
 * Use this command to check for messages from other players or the game engine.
 */
public class V0Xmail implements ApiCommand {
    public String name() {
        return "xmail";
    }

    public String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap) {
        return "not implemented";
    }
}
