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

    public int unload(World world) {
        return tons;
    }

    public int load(World sourceWorld, Ship targetShip, int skillDM /*Playable player*/) {
        Object[] tradeCodes = sourceWorld.tradeCodes();
        int tcCount = tradeCodes.length + 1;

        tons = (flux() + sourceWorld.popDigit()) * tcCount
                + skillDM // player.getSkillLevel("liaison")
                + targetShip.getPassageDemand();

        if (tons < 0) tons = 0;

        return tons;
    }
}
