package net.eagle.tas.tradersb.trade;

import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.world.World;

import java.util.ArrayList;

/**
 * Cargo is defined as speculative freight. Its buy value depends on the world of origin,
 * and its sale value depends on the origin and destination world.
 */
public class Cargo implements Shippable {
    private int tons = 0;
    public int sourceTL = 0;
    public int buyPrice = 0;

    private ArrayList<TradeCode> tradeCodes = new ArrayList<>();

    public ArrayList<TradeCode> getTradeCodes() {
        return tradeCodes;
    }

    public void addTradeCode(TradeCode tc) {
        tradeCodes.add(tc);
    }

    public boolean hasTradeCode(TradeCode tc) {
        return tradeCodes.contains(tc);
    }

    public int getCount() {
        return tons;
    }

    public int buyPrice(Ship ship, World sourceWorld) {
        return buyPrice;
    }

    public int unload(World world) {
        return 0;
    }

    public int load(World sourceWorld, Ship targetShip, int skillDM) {
        return 0;
    }
}
