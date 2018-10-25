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
                        .mission("A")
                        .sizeCode("B")
                        .config("U")
                        .fuel(21)
                        .mid(8)
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

            case "CM":
            case "Leviathan": // merchant cruiser
                return ShipFactory.createShipFromQSP("Leviathan R2-RC33 600 250 t 12/12/20");

            case "M": // subsidized liner
            case "Liner":
                return ShipFactory.createShipFromQSP("Liner M-FS31 210 100 t 30/10/30");

            case "R": // subsidized merchant
            case "Merchant":
                return ShipFactory.createShipFromQSP("Merchant R-DS11 44 100 t 16/4/20");

            case "S": // scout
            case "Scout":
                return ShipFactory.createShipFromQSP("Scout S-AA22 22 10 f 0/4/0 ");

        }
    }

    /**
     * Or, if you're a Traveller nerd, you can have a ship created to your specifications.
     * Just pass in the Quick Ship Profile of the ship you want, and Jack's a donut,
     * our pops your ship, fresh and ready to use.
     * <p>
     * An *ideal* QSP looks like this:
     * <p>
     * Marava A2-BS11 fuel hold vault h/m/l
     * |    |  ||||
     * Class name -----    |  ||||
     * Mission -------------  ||||
     * Size -------------------|||
     * Hull Configuration ------||
     * Jump rating --------------|
     * Maneuver rating -----------
     */
    public static Ship createShipFromQSP(String extendedQSP) {
        String[] elements = extendedQSP.split(" ");
        String className = elements[0];
        String[] qsp = elements[1].split("-");
        String mission = qsp[0];
        int fuel = Integer.parseInt(elements[2]);
        int hold = Integer.parseInt(elements[3]);
        boolean vault = Boolean.parseBoolean(elements[4]);
        String[] pass = elements[5].split("/");

        String size = qsp[1].substring(0, 1);
        String config = qsp[1].substring(1, 2);
        int jump = Integer.parseInt(qsp[1].substring(2, 3));
        int manu = Integer.parseInt(qsp[1].substring(3, 4));

        Ship ship = ShipBuilder.create()
                .shipClass(className)
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
                .build();

        return ship;
    }
}
