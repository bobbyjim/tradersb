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

    public void setId(String id) {
        this.id = id;
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

    public void setFreight(Shippable freight) {
        this.freight = freight;
    }

    public Shippable getHighPassengers() {
        return highPassengers;
    }

    public void setHighPassengers(Shippable highPassengers) {
        this.highPassengers = highPassengers;
    }

    public Shippable getMidPassengers() {
        return midPassengers;
    }

    public void setMidPassengers(Shippable midPassengers) {
        this.midPassengers = midPassengers;
    }

    public Shippable getLowPassengers() {
        return lowPassengers;
    }

    public void setLowPassengers(Shippable lowPassengers) {
        this.lowPassengers = lowPassengers;
    }

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

    private Shippable freight = new Freight();
    private Shippable highPassengers = new HighPassengers();
    private Shippable midPassengers = new MidPassengers();
    private Shippable lowPassengers = new LowPassengers();

    private boolean hasVault = false;
    private Cargo cargo = new Cargo();

    public String getId() {
        return id;
    }

    public void update(Ship ship) {
        this.name = ship.name;
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


}
