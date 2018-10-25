package net.eagle.tas.tradersb.map;

import net.eagle.tas.tradersb.world.World;
import net.eagle.tas.tradersb.world.WorldIsMissingDataException;

public interface MapAccessible {
    World /*HashMap<String,Object>*/ getWorld(String sector, String hex) throws WorldIsMissingDataException;

    World[] getJumpMap(World currentWorld, String sector, String hex, int jumpnum);
}
