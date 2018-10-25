package net.eagle.tas.tradersb.player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Actually more like a Player Broker.  Handles creation and fetching of player.
 */
public class PlayerFactory {
    public static Playable createPlayer(String playerType) {
        /*switch( playerType )
        {
            case "HTTP":
                return new WebClient();
            case "CLI":
            default:
                return new CLI();
        }*/
        return new Player();
    }

    public static Playable getPlayer(String playerID) {
        return getPlayer(playerID, "CLI");
    }

    /**
     * TODO: Complicated and in need of refactoring.
     * <p>
     * Attempts to deserialize a player; if there's an exception, then the player doesn't exist
     * or the format has changed, so create a new one with the given name.
     *
     * @param playerID   player name
     * @param playerType CLI or HTTP
     * @return Playable player object
     */
    public static Playable getPlayer(String playerID, String playerType) {
        String filename = playerID + ".ser";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Playable player = (Playable) ois.readObject();
            ois.close();
            System.err.println("Player object " + playerID + " read.");
            return player;
        } catch (Exception e) {
            System.err.println("Error reading player " + playerID + ": " + e.getMessage());
            // e.printStackTrace();
        }
        System.err.println("Creating new player " + playerID);
        Playable player = createPlayer(playerType);
        player.setPlayerName(playerID);
        return player;
    }

    /**
     * Attempts to serialize the player object to disk.
     *
     * @param player Playable player object
     */
    public static void savePlayer(Playable player) {
        String filename = player.getPlayerName() + ".ser";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(player);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.err.println("Error writing player " + player.getPlayerName() + ": " + e.getMessage());
        }
    }
}
