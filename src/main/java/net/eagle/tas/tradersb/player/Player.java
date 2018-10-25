package net.eagle.tas.tradersb.player;

import net.eagle.tas.tradersb.ship.*;
import net.eagle.tas.tradersb.trade.*;
import net.eagle.tas.tradersb.world.*;

import javax.persistence.Id;
import java.util.HashMap;

/**
 * The primary class to handle player interactions.  It's preferable to use one of its
 * subclasses, which are tailored to handle specific environments -- CLI and WebClient.
 * <p>
 * Since player information is stored locally, his ID is simply his name.  Routing messages
 * could prove problematic, if I don't use the hostname as a qualifier.
 * <p>
 * A player's current location is in "world".  His ship is in "ship".
 */
class Player implements Playable {
    @Id
    public String id;

    String playerName = "Jameson";

    private HashMap<String, Integer> skills = new HashMap<>();
    public World world;
    boolean unloaded = false; // TODO: this should be an Enum state: unloaded, loaded, in_transit or something
    public Ship ship = ShipFactory.createShip("Far Trader");
    public double mcr = 1.0;

    /**
     * Builds a player with reasonable defaults.
     */
    Player() {
        world = WorldBuilder.BuildRegina();

        skills.put("admin", 1);
        skills.put("liaison", 1);
        skills.put("steward", 1);
        skills.put("streetwise", 1);
    }

    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String id) {
        this.playerName = id;
    }

    public World getWorld() {
        return world;
    }

    public String setWorld(World w) {
        this.world = w;
        this.unloaded = false;
        return "Destination: " + w.name;
    }

    public Ship getShip() {
        return ship;
    }

    /**
     * Load a ship with freight, passengers, and speculative cargo.
     *
     * @return a string with the stuff loaded onto your ship.
     */
    public String loadShip() {
        String out = "";
        if (this.unloaded) {
            out = "\nLoading freight...";
            ship.getFreight().load(this);
            out += "   " + ship.getFreight().getCount() + " tons.";

            out += "\nLoading passengers...";
            ship.getLowPassengers().load(this);
            out += "\n   " + ship.getLowPassengers().getCount() + " low";
            ship.getMidPassengers().load(this);
            out += "\n   " + ship.getMidPassengers().getCount() + " mid";
            ship.getHighPassengers().load(this);
            out += "\n   " + ship.getHighPassengers().getCount() + " high";

            out += "\nLoading Speculative Cargo...";
            ship.setCargo(CargoBuilder.buildCargo(world));
            out += "\n   Buy price per ton: " + ship.getCargo().buyPrice;
        }
        return out;
    }

    /**
     * Unload passengers, freight, and speculative cargo.
     *
     * @return a string with the stuff unloaded from your ship.
     */
    public String unloadShip() {
        String out = "";
        if (!unloaded) {
            out = "\nUnloading passengers...";
            ship.getHighPassengers().unload(world);
            ship.getMidPassengers().unload(world);
            ship.getLowPassengers().unload(world);
            out += "done.";

            out += "\nUnloading freight...";
            ship.getFreight().unload(world);
            out += "done.";

            out += "\nUnloading Speculative Cargo:";
            Trade trade = TradeBuilder.buildTrade(ship.getCargo(), world);
            out += "\nSpec Cargo sale price:   " + trade.getSalePrice();
            out += "\nSpec Cargo origin price: " + ship.getCargo().buyPrice;
            int net = trade.getSalePrice() - ship.getCargo().buyPrice;
            if (net < (int) (Math.random() * 1000)) // simulate the time value of money, Monte Carlo style.
                out += "\n   Will not sell cargo.";
            else
                out += "\n   Net profit: Cr " + net + " per ton.";

            unloaded = true;
        }
        return out;
    }

    public String youAreHere() {
        return "\nWelcome to " + world.name + " (" + world.uwp + "/" + world.sectorAbbreviation + " " + world.hex + ")";
    }

    /**
     * print out a numbered list of worlds within range of your current location.
     *
     * @param worlds list of neighboring World objects.
     * @return a string containing the destination list.
     */
    public String printDestinations(World[] worlds) {
        String out = "\nDestinations in range:";
        for (int i = 0; i < worlds.length; i++) {
            String index = pad(i + "", 2);
            out += "\n " + index + ": " + worlds[i].toString();
        }
        return out;
    }

    private String pad(String in, int length) {
        while (in.length() < length)
            in = in.concat(" ");
        return in;
    }

    public void jump(World[] worlds) {
        /* do nothing */
    }

    public int getSkillLevel(String skill) {
        if (skills.containsKey(skill.toLowerCase()))
            return skills.get(skill.toLowerCase());
        else
            return 0; // default
    }
}
