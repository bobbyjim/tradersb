package net.eagle.tas.tradersb.ship;

public class ShipValidator
{
    // I don't trust people.

    public static void validateShip(Ship ship) throws ShipHasProblemException
    {
        String problems = "";

        if (ship.getJump() > 6) problems += "Jump range is greater than 6.\n";

        if ( problems.length() > 0 )
        {
            throw new ShipHasProblemException(problems);
        }
    }
}
