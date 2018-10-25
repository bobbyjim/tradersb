package net.eagle.tas.tradersb.trade;


import net.eagle.tas.tradersb.world.World;

/**
 * A trade's value depends on a matrix of trade codes between the source world
 * and the destination world.  A trade represents that by first noting the trade
 * codes present in the market world, and comparing them with the presence of
 * complementary trade codes in the cargo's source manifest.  The number of matches
 * determines the sale price.
 */
public class TradeBuilder {
    private Trade trade;

    public static Trade buildTrade(Cargo cargo, World market) {
        TradeBuilder builder = new TradeBuilder(cargo, market);
        return builder.trade;
    }

    private TradeBuilder(Cargo cargo, World market) {
        trade = new Trade();
        trade.cargo = cargo;
        trade.marketTL = market.TL();
        String[] tradeCodes = market.tradeCodes();

        for (String tc : tradeCodes) {
            switch (tc) {
                case "Ag":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Po);
                    add(cargo, TradeCode.Ri);
                    break;

                case "As":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.As);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Na);
                    add(cargo, TradeCode.Va);
                    break;

                case "De":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.De);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Na);
                    add(cargo, TradeCode.Ri);
                    break;

                case "Fl":
                    add(cargo, TradeCode.Fl);
                    add(cargo, TradeCode.In);
                    break;

                case "Hi":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.Hi);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Po);
                    add(cargo, TradeCode.Ri);
                    break;

                case "In":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.As);
                    add(cargo, TradeCode.Ba);
                    add(cargo, TradeCode.Fl);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Po);
                    add(cargo, TradeCode.Ri);
                    add(cargo, TradeCode.Va);
                    break;

                case "Ri":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.As);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Po);
                    add(cargo, TradeCode.Ri);
                    break;

                case "Va":
                    add(cargo, TradeCode.Ag);
                    add(cargo, TradeCode.As);
                    add(cargo, TradeCode.In);
                    add(cargo, TradeCode.Na);
                    add(cargo, TradeCode.Va);
                    break;

            }
        }
    }

    private void add(Cargo cargo, TradeCode tc) {
        if (cargo != null && cargo.hasTradeCode(tc))
            trade.tradeCodes.add(tc);
    }
}
