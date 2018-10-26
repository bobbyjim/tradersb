package net.eagle.tas.tradersb.trade;

public class PassengerFactory {

    /**
     * High Passage is luxury travel for the discriminating passenger.
     * High Passengers prefer crew with Steward skills.
     * The higher your ship's demand rating is, the more passengers will pay to travel with you.
     */
    public static Passengers createHighPassengersObject() {
        return new Passengers(1000, 10000, "steward");
    }

    /**
     * Passengers who aren't rich enough to afford High Passage.
     * Admin skill attracts these sorts of passengers.
     */
    public static Passengers createMidPassengersObject() {
        return new Passengers(1000, 8000, "admin");
    }

    /**
     * Passengers shipped via freezer.
     * Low passengers are recruited better if you have Streetwise skill.
     * The higher your ship's demand rating is, the more passengers will pay to travel with you.
     */
    public static Passengers createLowPassengersObject()
    {
        return new Passengers(100, 1000, "streetwise");
    }
}
