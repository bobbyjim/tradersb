package net.eagle.tas.tradersb.ship;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ShipDAOService {
    private static List<Ship> ships = new ArrayList<>();

    static {
        ships.add(ShipFactory.createShip("A"));
        ships.add(ShipFactory.createShip("A2"));
        ships.add(ShipFactory.createShip("C"));
        ships.add(ShipFactory.createShip("CM"));
        ships.add(ShipFactory.createShip("E"));
        ships.add(ShipFactory.createShip("F"));
        ships.add(ShipFactory.createShip("G"));
        ships.add(ShipFactory.createShip("J"));
        ships.add(ShipFactory.createShip("K"));
        ships.add(ShipFactory.createShip("L"));
        ships.add(ShipFactory.createShip("M"));
        ships.add(ShipFactory.createShip("M2"));
        ships.add(ShipFactory.createShip("P"));
        ships.add(ShipFactory.createShip("R"));
        ships.add(ShipFactory.createShip("S"));
        ships.add(ShipFactory.createShip("T"));
        ships.add(ShipFactory.createShip("U"));
        ships.add(ShipFactory.createShip("W"));
        ships.add(ShipFactory.createShip("X"));
        ships.add(ShipFactory.createShip("Y"));
    }

    public List<Ship> getAllShips() {
        return ships;
    }

    public Ship getShip(String id) throws ShipNotFoundException {
        Ship ship = ships.stream().filter(s -> id.equals(s.getId())).findFirst().orElse(null);
        if (ship == null) {
            throw new ShipNotFoundException();
        }
        return ship;
    }

    public Ship updateShip(String id, Ship params) throws CannotUpdateShipException {
        Ship ship = null;
        try {
            ship = getShip(id);
            if (ship == null)
                throw new ShipNotFoundException();

            ship.update(params);
        } catch (ShipNotFoundException snfe) {
            throw new CannotUpdateShipException();
        }

        return ship;
    }
}
