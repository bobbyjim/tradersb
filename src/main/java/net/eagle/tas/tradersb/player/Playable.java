package net.eagle.tas.tradersb.player;

import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.world.World;

import java.io.Serializable;

public interface Playable extends Serializable {
    String getPlayerName();

    void setPlayerName(String id);

    World getWorld();

    String setWorld(World w);

    Ship getShip();

    String youAreHere();

    String unloadShip();

    String loadShip();

    String printDestinations(World[] worlds);

    void jump(World[] worlds);

    int getSkillLevel(String skill);
}
