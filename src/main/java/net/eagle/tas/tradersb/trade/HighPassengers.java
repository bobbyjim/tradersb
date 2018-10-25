package net.eagle.tas.tradersb.trade;

import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.world.World;

/**
 * High Passage is luxury travel for the discriminating passenger.
 * High Passengers prefer crew with Steward skills.
 * The higher your ship's demand rating is, the more passengers will pay to travel with you.
 */
public class HighPassengers implements Shippable {
    public int count = 0;

    public int getCount() {
        return count;
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
        return ship.getPassageDemand() * 1000 + 10000;
    }

    public void unload(World world) {
    }

    public void load(Playable player) {
        count = flux()
                + player.getWorld().popDigit()
                + player.getSkillLevel("steward")
                + player.getShip().getPassageDemand();

        if (count < 0) count = 0;
    }
}
