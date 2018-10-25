package net.eagle.tas.tradersb.trade;

import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.world.World;

/**
 * Freight is flat-rate cargo, shipped between worlds.
 */
public class Freight implements Shippable {
    private int tons = 0;

    public int getCount() {
        return tons;
    }

    private int flux() {
        int a = (int) (Math.random() * 6);
        int b = (int) (Math.random() * 6);
        return a - b;
    }

    public int buyPrice(Ship ship, World world) {
        return 0;
    }

    public int sellPrice(Ship ship, World world) {
        return ship.getPassageDemand() * 1000 + 8000;
    }

    public void unload(World world) {
    }

    public void load(Playable player) {
        Object[] tradeCodes = player.getWorld().tradeCodes();
        int tcCount = tradeCodes.length + 1;

        tons = (flux() + player.getWorld().popDigit()) * tcCount
                + player.getSkillLevel("liaison")
                + player.getShip().getPassageDemand();

        if (tons < 0) tons = 0;

    }
}
