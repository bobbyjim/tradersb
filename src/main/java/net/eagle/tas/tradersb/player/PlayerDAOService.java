package net.eagle.tas.tradersb.player;

import net.eagle.tas.tradersb.map.MapAccessible;
import net.eagle.tas.tradersb.map.MapFactory;
import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.ship.ShipHasProblemException;
import net.eagle.tas.tradersb.ship.ShipValidator;
import net.eagle.tas.tradersb.util.UniqueGenerator;
import net.eagle.tas.tradersb.world.World;
import net.eagle.tas.tradersb.world.WorldIsMissingDataException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PlayerDAOService {

    private HashMap<String, Player> players = new HashMap<>();

    public List<Player> getAllPlayers() {
        return new ArrayList<Player>(players.values());
    }

    public Player getPlayer(String id) throws PlayerNotFoundException {
        if (players.containsKey(id)) {
            return players.get(id);
        }
        // else:
        File file = new File("player-" + id + ".ser");
        boolean exists = file.exists();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object player = ois.readObject();
            ois.close();
            players.put(id, (Player) player);
            return (Player) player;
        } catch (Exception e) {
            throw new PlayerNotFoundException();
        }
    }

    public Player createPlayer() {
        Player player = new Player();
        player.id = UniqueGenerator.generateShortID();
        players.put(player.id, player);
        save(player);
        return player;
    }

    private boolean save(Player player) {
        String filename = "player-" + player.id + ".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(player);
            oos.flush();
            return true;
        } catch (Exception e) {
            // return player save exception
        }
        return false;
    }

    public void improveSkill( String id, String skill ) throws PlayerNotFoundException
    {
        Player player = getPlayer(id);
        player.improveSkill(skill);
        save(player);
    }

    public World jump(String id, World destination) throws PlayerNotFoundException, WorldIsMissingDataException {
        Player player = getPlayer(id);
        if (player.getWorld().distanceTo(destination) <= player.getShip().getJump()) {
            MapAccessible map = MapFactory.defaultMapEngine;
            destination = map.getWorld(destination.sectorAbbreviation, destination.hex);
            player.setWorld(destination);
            save(player);
        }
        return player.getWorld();
    }

    public Ship updateShip(String id, Ship updatedShip) throws PlayerNotFoundException, ShipHasProblemException {

        Player player = getPlayer(id);
        ShipValidator.validateShip(updatedShip);
        player.ship = updatedShip;
        save(player);
        return player.ship;
    }
}
