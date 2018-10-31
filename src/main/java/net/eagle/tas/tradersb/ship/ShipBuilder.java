package net.eagle.tas.tradersb.ship;

/**
 * Created by eagro02 on 10/17/2017.
 * A handy way to build a ship.
 */
public class ShipBuilder {
    Ship newship;

    public static ShipBuilder create() {
        ShipBuilder builder = new ShipBuilder();
        builder.newship = new Ship();
        return builder;
    }

    public Ship build() {
        newship.setName(newship.getShipClass() + "-" + newship.getId()); // for now, set initial name == class + "-" + id
        return newship;
    }

    /**
     * Build Methods
     **/
    public ShipBuilder id(String id) {
        newship.setId( id );
        return this;
    }

    public ShipBuilder shipClass(String name) {
        newship.setShipClass(name);
        return this;
    }

    public ShipBuilder sizeCode(String size) {
        newship.setSizeCode(size);
        return this;
    }

    public ShipBuilder mission(String mission) {
        newship.setMission(mission);
        return this;
    }

    public ShipBuilder config(String config) {
        newship.setConfig(config);
        return this;
    }

    public ShipBuilder passageDemand(int demand) {
        newship.setPassageDemand(demand);
        return this;
    }

    public ShipBuilder crewComfort(int comfort) {
        newship.setCrewComfort(comfort);
        return this;
    }

    public ShipBuilder fuel(int fuel) {
        newship.setFuel(fuel);
        return this;
    }

    public ShipBuilder jump(int jump) {
        newship.setJump(jump);
        return this;
    }

    public ShipBuilder maneuver(int maneuver) {
        newship.setManeuver(maneuver);
        return this;
    }

    public ShipBuilder hold(int hold) {
        newship.setHold(hold);
        return this;
    }

    public ShipBuilder high(int high) {
        newship.setHigh(high);
        return this;
    }

    public ShipBuilder mid(int mid) {
        newship.setMid(mid);
        return this;
    }

    public ShipBuilder low(int low) {
        newship.setLow(low);
        return this;
    }

    public ShipBuilder hasVault(boolean vault) {
        newship.setVault(vault);
        return this;
    }

    public ShipBuilder cost(int mcr) {
        newship.setCost(mcr);
        return this;
    }
}