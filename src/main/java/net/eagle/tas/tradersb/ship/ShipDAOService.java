package net.eagle.tas.tradersb.ship;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ShipDAOService {
    private static List<Ship> ships = new ArrayList<>();

    static {
        ships.add(ShipFactory.createShip("Beowulf"));
        ships.add(ShipFactory.createShip("Marava"));
        ships.add(ShipFactory.createShip("Leviathan"));
        ships.add(ShipFactory.createShip("Liner"));
        ships.add(ShipFactory.createShip("Scout"));
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
