package net.eagle.tas.tradersb.trade;

import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.world.World;

import java.io.Serializable;

public interface Shippable extends Serializable {
    int getCount();

    int buyPrice(Ship ship, World world);

    //    int sellPrice( Interstellar ship, World world );
    void unload(World world);

    void load(Playable player);
}
