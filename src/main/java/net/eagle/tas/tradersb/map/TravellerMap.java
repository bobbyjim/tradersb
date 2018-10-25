package net.eagle.tas.tradersb.map;

import net.eagle.tas.tradersb.world.World;
import net.eagle.tas.tradersb.world.WorldBuilder;
import net.eagle.tas.tradersb.world.WorldIsMissingDataException;
import org.json.simple.parser.JSONParser;
import org.json.simple.util.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Travellermap.com is a service provided by Joshua Bell.  It is, essentially, a REST-based
 * map engine with a rich set of operations.  This class talks to it to retrieve info
 * about the world you're currently at, as well as a list of nearby worlds.
 */
public class TravellerMap implements MapAccessible {
    private JSONParser parser = new JSONParser();

    public World getWorld(String sector, String hex) throws WorldIsMissingDataException {
        String json = get("https://travellermap.com/data/" + sector + "/" + hex);
        HashMap<String, Object> hm = Mapper.decode(json);
        ArrayList array = (ArrayList) hm.get("Worlds");
        Object o1 = array.get(0);
        return WorldBuilder.build((HashMap) o1);
    }

    public World[] getJumpMap(World currentWorld, String sector, String hex, int jumpnum) {
        String json = get("https://travellermap.com/data/" + sector + "/" + hex + "/jump/" + jumpnum);
        HashMap<String, Object> out = Mapper.decode(json);

        ArrayList array = (ArrayList) out.get("Worlds");
        ArrayList<World> worlds = new ArrayList<>();

        int i = 0;
        for (Object o1 : array)
            try {
                World world = WorldBuilder.build((HashMap) o1);
                int dist = world.distanceTo(currentWorld);
                if (dist > 0) // exclude current world
                {
                    worlds.add(world);
                }
            } catch (WorldIsMissingDataException wimde) { /* don't add to list */ }

        World[] outWorlds = new World[1];
        outWorlds = worlds.toArray(outWorlds);

        return outWorlds;
    }

    private String get() {
        return get();
    }

    private String get(String urlString) {
        HttpURLConnection conn = connect(urlString);
        checkConnectionIntegrity(conn);
        String output = readURLResponse(conn);
        if (conn != null)
            conn.disconnect();
        return output;
    }

    private HttpURLConnection connect(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            return conn;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private void checkConnectionIntegrity(HttpURLConnection conn) {
        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code: "
                        + conn.getResponseCode());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private String readURLResponse(HttpURLConnection conn) {
        String output = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return output;
    }
}
