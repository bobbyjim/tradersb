package net.eagle.tas.tradersb.ship;

public class ShipValidator
{
    // I don't trust people.

    public static void validateShip(Ship ship) throws ShipHasProblemException
    {
        String problems = "";

        if (ship.getJump() > 9) problems += "Jump rating is greater than 9.\n";
        if (ship.getManeuver() > 9) problems += "Maneuver rating is greater than 9.\n";
        
        try {
            ship.getTonnage();
        }
        catch( ShipHasProblemException shpe )
        {
            problems += shpe.getMessage() + "\n";
        }

        if ( problems.length() > 0 )
        {
            throw new ShipHasProblemException(problems);
        }
    }
}
