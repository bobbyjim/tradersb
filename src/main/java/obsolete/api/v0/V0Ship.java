package obsolete.api.v0;

import obsolete.api.ApiCommand;
import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.player.PlayerFactory;
import net.eagle.tas.tradersb.world.World;
import obsolete.client.TraderClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Use this command to get information on your ship.
 */
public class V0Ship implements ApiCommand {
    public String name() {
        return "ship";
    }

    public String handle(String[] path, Map<String, Object> parameters, HashMap<String, Object> jsonMap) {
        //
        //  Proof-of-Concept:
        //
        Jump jump = splitPath(path);

        Playable player = PlayerFactory.getPlayer(jump.playerID, "HTTP");
        TraderClient client = new TraderClient(player);
        World[] worlds = client.scan(client.player);

        String response =
                player.youAreHere()
                        + player.unloadShip();

        if (jump.destination > -1) {
            // we have a destination
            response += player.loadShip();
            response += player.setWorld(worlds[jump.destination]) + "\n";
        } else {
            // show destination list
            response += player.printDestinations(worlds);
        }

        PlayerFactory.savePlayer(player);
        //
        //
        //

        return response;
    }

    private Jump splitPath(String[] path) {
        String version = path[1];
        String stemNoun = path[2];
        String playerID = path[3];
        int destination = -1;
        if (path.length > 4)
            destination = Integer.parseInt(path[4]);
        Jump jump = new Jump();
        jump.playerID = playerID;
        jump.destination = destination;
        return jump;
    }

    class Jump // doesn't do a lot... for now
    {
        public String playerID = "";
        public int destination = 0;
    }
}
