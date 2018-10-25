package net.eagle.tas.tradersb.trade;

public enum TradeCode {
    Ag(-1000),
    As(-1000),
    Ba(1000),
    De(1000),
    Fl(1000),
    Hi(-1000),
    Ic(0),
    In(-1000),
    Lo(1000),
    Na(1000),
    Ni(1000),
    Po(-1000),
    Ri(1000),
    Va(1000);

    public int buyMod;
    //public boolean appliesToSource;
    //public boolean appliesToDestination;

    TradeCode(int buyMod) {
        this.buyMod = buyMod;
        //this.appliesToSource = false;
        //this.appliesToDestination = false;
    }

    public int getBuyModifier() {
        //if ( appliesToSource )
        return buyMod;
        //else
        //  return 0;
    }

/*    public int getSellModifier()
    {
        if ( appliesToSource && appliesToDestination )
            return 1000;
        else
            return 0;
    }*/
}
