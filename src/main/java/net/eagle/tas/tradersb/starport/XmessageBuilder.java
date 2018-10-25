package net.eagle.tas.tradersb.starport;


import net.eagle.tas.tradersb.player.Playable;

import java.util.Date;

public class XmessageBuilder {
    private Xmessage xmessage = new Xmessage();

    public XmessageBuilder(Playable originator) {
        xmessage.sender = originator;
        xmessage.originWorld = originator.getWorld();
        xmessage.created = new Date();
    }

    public Xmessage create(String message) {
        xmessage.message = message;
        xmessage.active = true;
        return xmessage;
    }
}
