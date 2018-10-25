package obsolete.api.v0;


import obsolete.api.ApiCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Person-status retriever, 11/29/2017.
 * Use this command to get information about your in-game character.
 */
public class V0Person implements ApiCommand {
    public String name() {
        return "person";
    }

    public String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap) {
        return "not implemented";
    }
}
