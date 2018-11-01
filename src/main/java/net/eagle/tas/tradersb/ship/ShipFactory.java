package net.eagle.tas.tradersb.ship;

/**
 * Created by eagro02 on 10/17/2017.
 * <p>
 * Builds the standard types of starships available.
 */
public class ShipFactory {
    public static Ship createShip(String type) {
        switch (type) {
            case "A":
            case "Trader":
            case "Free Trader":
            case "Beowulf":
            default:
                return ShipBuilder.create()
                        .shipClass("Beowulf")
                        .id("A-BS11")
                        .mission("A")
                        .sizeCode("B")
                        .config("U")
                        .fuel(21)
                        .mid(6)
                        .low(10)
                        .hold(82)
                        .jump(1)
                        .maneuver(1)
                        .build();

            case "A2":
            case "Far Trader":
            case "Marava":
                return ShipBuilder.create()
                        .shipClass("Marava")
                        .id("A2-BS12")
                        .mission("A2")
                        .sizeCode("B")
                        .config("S")
                        .fuel(41)
                        .mid(6)
                        .low(4)
                        .hold(64)
                        .jump(2)
                        .maneuver(1)
                        .build();

            case "Maada": return ShipFactory.createShipFromQSP("Maada A-BS11 23 60 f 8/0/4 41");
            case "Eakhau": return ShipFactory.createShipFromQSP("Eakhau A-DS12 84 162 t 8/2/16 93");
            case "C":  return ShipFactory.createShipFromQSP("Cruiser C-HU33 276 25 t 2/2/10 300");
            case "Leviathan": // merchant cruiser
            case "R2": return ShipFactory.createShipFromQSP("Leviathan R2-TB43 612 140 t 4/2/4 639");
            case "E":  return ShipFactory.createShipFromQSP("Gazelle E-DS53 140 2 t 0/0/10 274" );
            case "Corvette": // patrol corvette
                       return ShipFactory.createShipFromQSP("Lurushaar EB-EA53 200 6 t 0/0/10 307");
            case "F":  return ShipFactory.createShipFromQSP("Susa F-KS13 317 417 t 0/12/10 229" );
            case "G":  return ShipFactory.createShipFromQSP("Frigate G-FS52 150 50 t 10/5/10" );
            case "J":  return ShipFactory.createShipFromQSP("Seeker J-AA11 11 16 f 0/2/2" );
            case "K":  return ShipFactory.createShipFromQSP("Safari K-BA12 22 25 t 8/4/4" );
            case "L":  return ShipFactory.createShipFromQSP("Lab L-DC12 88 150 f 10/20/4" );
            case "Liner":
            case "M":  return ShipFactory.createShipFromQSP("Liner M-FS13 210 100 t 30/10/30");
            case "M2": return ShipFactory.createShipFromQSP("Long-Liner M2-ZS14 1200 1500 t 20/20/30");
            case "P":  return ShipFactory.createShipFromQSP("Corsair P-DS42 96 100 t 0/10/10");
            case "Merchant":
            case "R":  return ShipFactory.createShipFromQSP("Merchant R-DS11 44 100 t 16/4/20");
            case "Scout":
            case "S":  return ShipFactory.createShipFromQSP("Scout S-AA22 22 8 f 0/4/0 ");
            case "T":  return ShipFactory.createShipFromQSP("Transport T-KU12 210 100 t 4/20/40");
            case "U":  return ShipFactory.createShipFromQSP("Packet U-CA33 99 25 t 8/8/8");
            case "W":  return ShipFactory.createShipFromQSP("Barge W-AC11 11 35 f 0/4/4");
            case "X":  return ShipFactory.createShipFromQSP("Xboat X-AC04 40 1 t 0/1/0");
            case "Y":  return ShipFactory.createShipFromQSP("Yacht Y-EL41 54 50 t 16/8/4");
        }
    }

    /**
     * Or, if you're a Traveller nerd, you can have a ship created to your specifications.
     * Just pass in the Quick Ship Profile of the ship you want, and Jack's a donut,
     * our pops your ship, fresh and ready to use.
     * <p>
     * An *ideal* QSP looks like this:
     * <p>
     * Marava A2-BS11 fuel hold vault h/m/l cost
     * |    |  ||||
     * Class name -----    |  ||||22
     * Mission -------------  ||||
     * Size -------------------|||
     * Hull Configuration ------||
     * Jump rating --------------|
     * Maneuver rating -----------
     */
    public static Ship createShipFromQSP(String extendedQSP) {
        String[] elements = extendedQSP.split(" ");
        String className = elements[0];
        String qspId = elements[1];
        String[] qsp = elements[1].split("-");
        String mission = qsp[0];
        int fuel = Integer.parseInt(elements[2]);
        int hold = Integer.parseInt(elements[3]);
        boolean vault = Boolean.parseBoolean(elements[4]);
        String[] pass = elements[5].split("/");
        int cost = 0;
        if ( elements.length > 6 ) cost = Integer.parseInt(elements[6]);

        String size = qsp[1].substring(0, 1);
        String config = qsp[1].substring(1, 2);
        int jump = Integer.parseInt(qsp[1].substring(2, 3));
        int manu = Integer.parseInt(qsp[1].substring(3, 4));

        Ship ship = ShipBuilder.create()
                .shipClass(className)
                .id(qspId)
                .sizeCode(size)
                .mission(mission)
                .hold(hold)
                .fuel(fuel)
                .jump(jump)
                .config(config)
                .maneuver(manu)
                .high(Integer.parseInt(pass[0]))
                .mid(Integer.parseInt(pass[1]))
                .low(Integer.parseInt(pass[2]))
                .hasVault(vault)
                .cost(cost)
                .build();

        return ship;
    }
}
