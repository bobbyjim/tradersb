package net.eagle.tas.tradersb.trade;

import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.world.World;

public class Passengers implements Shippable {
    public int count = 0;

    private int premiumDemandPrice;
    private int baseSellPrice;
    private String skillDM;

    public Passengers() {}

    public Passengers(int premiumDemandPrice, int baseSellPrice, String skillDM) {
        this.premiumDemandPrice = premiumDemandPrice;
        this.baseSellPrice = baseSellPrice;
        this.skillDM = skillDM;
    }

    public int getCount() {
        return count;
    }

    public void setPremiumDemandPrice(int premiumDemandPrice) {
        this.premiumDemandPrice = premiumDemandPrice;
    }

    public void setBaseSellPrice(int baseSellPrice) {
        this.baseSellPrice = baseSellPrice;
    }

    public void setSkillDM(String skillDM) {
        this.skillDM = skillDM;
    }

    public int buyPrice(Ship ship, World world) {
        return 0;
    }

    public int sellPrice(Ship ship, World world) {
        return ship.getPassageDemand() * premiumDemandPrice + baseSellPrice;
    }

    public int unload(World world) {
        return 0;
    }

    public int load(World sourceWorld, Ship targetShip, int skillDM /*Playable player*/) {
        count = flux()
                + sourceWorld.popDigit()
                + skillDM // getAppropriateSkillLevel(player)
                + targetShip.getPassageDemand();

        if (count < 0) count = 0;
        return count;
    }

    private int flux() {
        int a = (int) (Math.random() * 6);
        int b = (int) (Math.random() * 6);
        return a - b;
    }

    /*
    private int getAppropriateSkillLevel(Playable player)
    {
        return player.getSkillLevel(skillDM);
    }
    */

}
