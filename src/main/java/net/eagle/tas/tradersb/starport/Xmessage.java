package net.eagle.tas.tradersb.starport;


import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.world.World;

import java.util.Date;

/**
 * A way to communicate with your buddies.
 */
public class Xmessage {
    Playable sender;
    Playable receiver;
    World originWorld;
    Date created;
    String message;
    boolean active;
}
