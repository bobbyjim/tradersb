package obsolete.api;

import java.util.HashMap;
import java.util.Map;

public interface ApiCommand {

    String name();

    String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap);

    String notImplemented = "not implemented";
    String unknownCommand = "unknown command";
}
