package obsolete.network;

import com.sun.net.httpserver.HttpServer;
import obsolete.api.API;
import obsolete.api.ApiVersionFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Common code for data servers, 11/29/2017.
 */
abstract class AbstractDataServer {
    AbstractDataServer(int port) throws IOException {
        V0Context context = buildV0Context(new V0Context(), ApiVersionFactory.apiV0factory());

        InetSocketAddress host = new InetSocketAddress(port);
        HttpServer server = HttpServer.create(host, 0);
        server.createContext("/v0/", context);
        server.setExecutor(null);
        server.start();
    }

    abstract V0Context buildV0Context(V0Context ctx, API fac);
}
