package obsolete.api.v0;

import obsolete.api.ApiCommand;

import java.util.HashMap;
import java.util.Map;

public class V0DoNothing implements ApiCommand {
    public String name() {
        return "do-nothing";
    }

    public String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap) {
        return "V0Ship waits.";
    }
}
