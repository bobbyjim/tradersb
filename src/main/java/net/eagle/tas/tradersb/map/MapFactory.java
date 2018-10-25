package net.eagle.tas.tradersb.map;

/**
 * While TravellerMap is the main resource for map data, this factory allows us to
 * route to other map sources, including any data we might want to cache.
 */
public class MapFactory {
    public static MapAccessible defaultMapEngine = new TravellerMap();

    public static MapAccessible getMapEngine(String name) {
        switch (name) {
            case "maps.TravellerMap":
                return defaultMapEngine;
        }
        return defaultMapEngine;
    }
}
