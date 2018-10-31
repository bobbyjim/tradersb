package net.eagle.tas.tradersb.ship;

import net.eagle.tas.tradersb.trade.*;

import javax.persistence.Id;
import java.io.Serializable;

public class Ship implements Serializable {

    @Id
    private String id;

    private String name = "";
    private String shipClass = "";
    private String sizeCode;

    private int fuel = 0;
    private int jump = 0;
    private int maneuver = 0;
    private int hold = 0;
    private int high = 0;
    private int mid = 0;
    private int low = 0;
    private String mission = "Z";
    private String config = "Z";
    private int passageDemand = 0;
    private int crewComfort = 0;
    int cost;

    private Freight freight = new Freight();
    private Passengers highPassengers = PassengerFactory.createHighPassengersObject();
    private Passengers midPassengers  = PassengerFactory.createMidPassengersObject();
    private Passengers lowPassengers  = PassengerFactory.createLowPassengersObject();

    private boolean hasVault = false;
    private Cargo cargo = new Cargo();

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void update(Ship ship) {
        this.name = ship.name;
    }

    public String getShipClass() {
        return shipClass;
    }

    public void setShipClass(String shipClass) {
        this.shipClass = shipClass;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public int getTonnage() throws ShipHasProblemException
    {
        switch( sizeCode )
        {
            case "A": return 100;
            case "B": return 200;
            case "C": return 300;
            case "D": return 400;
            case "E": return 500;
            case "F": return 600;
            case "G": return 700;
            case "H": return 800;
            case "J": return 900;
            case "K": return 1000;
            case "L": return 1100;
            case "M": return 1200;
            case "N": return 1300;
            case "P": return 1400;
            case "Q": return 1500;
            case "R": return 1600;
            case "S": return 1700;
            case "T": return 1800;
            case "U": return 1900;
            case "V": return 2000;
            case "W": return 2100;
            case "X": return 2200;
            case "Y": return 2300;
            case "Z": return 2400;
        }
        throw new ShipHasProblemException("Ship size code is invalid (" + sizeCode + ")");
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getManeuver() {
        return maneuver;
    }

    public void setManeuver(int maneuver) {
        this.maneuver = maneuver;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public int getPassageDemand() {
        return passageDemand;
    }

    public void setPassageDemand(int passageDemand) {
        this.passageDemand = passageDemand;
    }

    public int getCrewComfort() {
        return crewComfort;
    }

    public void setCrewComfort(int crewComfort) {
        this.crewComfort = crewComfort;
    }

    public Shippable getFreight() {
        return freight;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }

    public Shippable getHighPassengers() {
        return highPassengers;
    }

    public void setHighPassengers(Passengers highPassengers) {
        this.highPassengers = highPassengers;
    }

    public Shippable getMidPassengers() {
        return midPassengers;
    }

    public void setMidPassengers(Passengers midPassengers) {
        this.midPassengers = midPassengers;
    }

    public Shippable getLowPassengers() {
        return lowPassengers;
    }

    public void setLowPassengers(Passengers lowPassengers) {
        this.lowPassengers = lowPassengers;
    }

    public void setVault(boolean vault) {
        this.hasVault = vault;
    }

    public boolean hasVault() {
        return hasVault;
    }


    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = null;
        this.cargo = cargo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(int mcr) { this.cost = mcr; }

    public int getCost() { return cost; }

}
