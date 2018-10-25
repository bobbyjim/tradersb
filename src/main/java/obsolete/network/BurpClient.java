package obsolete.network;

import java.io.*;
import java.net.*;
import java.util.*;

/********************************************************************

 BURP - Barely Usable Remote Protocol
 v1.0

 Commands:
 quit!             Close connection


 Gets:
 *                 Get everything
 /pattern/         Get everything matching a pattern
 key               Get $state{key}


 Sets:
 k=v               Set one key/value pair
 k=v&k=v&k=v...    Set multiple key/value pairs

 ********************************************************************/
public class BurpClient // v1.0
{
    Socket connection = null;
    PrintWriter pw = null;
    BufferedReader br = null;

    /****************************************************************
     *
     *  Create a connection to a host at a given port, and
     *  create the input & output objects.
     *
     ****************************************************************/
    public BurpClient(String host, int port) throws Exception {
        connection = new Socket(host, port);
        connection.setTcpNoDelay(true);

        pw = new PrintWriter(connection.getOutputStream());
        br = new BufferedReader
                (
                        new InputStreamReader(connection.getInputStream())
                );
    }

    /****************************************************************
     *
     *  Send the server a command.
     *
     ****************************************************************/
    public void command(String cmd) {
        send(cmd + "!");
    }

    /****************************************************************
     *
     *  Get all pairs.
     *
     ****************************************************************/
    public String getAll() {
        return send("*");
    }

    /****************************************************************
     *
     *  Get all pairs conforming to the given pattern.
     *
     ****************************************************************/
    public String getPattern(String pattern) {
        return send("/" + pattern + "/");
    }

    /****************************************************************
     *
     *  Get the value corresponding to the given key.
     *
     ****************************************************************/
    public String get(String key) {
        return send(key);
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt(get(key));
        } catch (Exception e) {
            return 0;
        }
    }

    /****************************************************************
     *
     *  Build and send a key/value string to the server.
     *  Return the response.
     *
     ****************************************************************/
    public String set(String key, String val) {
        return send(key + "=" + val);
    }

    public String set(String key, int val) {
        return send(key + "=" + val);
    }

    public String set(String key, double val) {
        return send(key + "=" + val);
    }

    public String clear(String key) {
        return send(key + "=");
    }

    /****************************************************************
     *
     *  Send a string to the server and return the response.
     *
     ****************************************************************/
    public String send(String msg) {
        String response = null;

        try {
            pw.println(msg);
            pw.flush();

            response = br.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }

        return response;
    }

    /****************************************************************
     *
     *  Wait for the server to send something.
     *
     ****************************************************************/
    public String recv() {
        try {
            return br.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }

        return null;
    }

    /****************************************************************
     *
     *  Parse a key-value response string into a hashtable.
     *  The response string is expected to be like an URL encoded
     *  parameter string:
     *
     *  "key=value&key=value&key=value..."
     *
     *  There are no uniqueness checks.  Drive defensively.
     *
     ****************************************************************/
    public HashMap<String, String> parse(String response) {
        HashMap<String, String> out = new HashMap<>();

        if (response == null)
            return out;

        StringTokenizer tok = new StringTokenizer(response, "&");

        while (tok.hasMoreTokens()) {
            String pair = tok.nextToken();
//         System.err.println( pair );
            int sign = pair.indexOf("=");
            if (sign > -1) {
                String key = pair.substring(0, sign);
                String val = pair.substring(sign + 1);
                out.put(key, val);
            }
        }
        return out;
    }

    /****************************************************************
     *
     *  Do a demo.  The command line should look like this:
     *
     *  java Client host-machine "somekey=somevalue"
     *
     *  or just
     *
     *  java Client host-machine ""
     *
     ****************************************************************/
    public static void main(String[] args) throws Exception {
        BurpClient client = new BurpClient(args[0], 8080);

        for (int i = 1; i < args.length; i++) {
            String response = client.send(args[i]);
            System.err.println("Response: " + response);
            System.err.println("\nParsed:\n");

            HashMap<String, String> data = client.parse(response);
            Set keys = data.entrySet();

            for (Object key : keys) {
                String val = data.get(key);
                System.err.println(key + " => " + val);
            }
        }
        client.command("quit");
    }
}