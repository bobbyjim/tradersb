package obsolete.network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import obsolete.api.*;
import org.json.simple.util.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Version 0 API handler, 11/29/2017.
 */
class V0Context implements HttpHandler {
    ArrayList<ApiCommand> commands = new ArrayList<>();

    public void handle(HttpExchange t) throws IOException {
        URI requestedUri = t.getRequestURI();

        // get parameters, if any
        Map<String, Object> parameters = Util.parseQuery(requestedUri.getRawQuery());

        // get stem noun
        String path = requestedUri.getPath();
        String[] pathArray = path.split("/");
        String version = pathArray[1];
        String stemNoun = pathArray[2];

        // get command based on stem noun from list of approved commands
        API api = ApiVersionFactory.apiV0factory();
        ApiCommand cmd = api.getDefaultCommand();
        for (ApiCommand command : commands) {
            if (stemNoun.equalsIgnoreCase(command.name())) {
                cmd = command;
                break;
            }
        }

        // read body, if any
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()));
        HashMap<String, Object> jsonMap = Mapper.decode(br);
        if (jsonMap.containsKey("Exception")) // nothing there -- throw on floor.
        {
            jsonMap = null;
        }

        // handle request
        String response = cmd.handle(pathArray, parameters, jsonMap);

        t.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
