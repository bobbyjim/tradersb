package obsolete.ships;


import net.eagle.tas.tradersb.trade.Cargo;
import net.eagle.tas.tradersb.trade.Shippable;

public interface Interstellar {
    String getMissionCode();

    String getConfiguration();

    int getJumpRange();

    int getManeuverRating();

    int getFuel();

    int getPassageDemand();

    int getCrewComfort();

    boolean hasVault();

    int freeCargoSpace();

    void setCargo(Cargo cargo);

    Shippable getFreight();

    Cargo getCargo();

    Shippable getHighPassengers();

    Shippable getMidPassengers();

    Shippable getLowPassengers();
}
