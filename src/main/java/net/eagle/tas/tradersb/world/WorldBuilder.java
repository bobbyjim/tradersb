package net.eagle.tas.tradersb.world;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by eagro02 on 10/17/2017.
 * <p>
 * Builds worlds in a polite way for you.
 * If you have world data stored in a HashMap, the constructor will build you a world from it.
 * <p>
 * It will also build the archetypical default world (Regina) for you, but that should really
 * be in a WorldFactory.
 */
public class WorldBuilder {
    World world;

    // This is the factory method for creating a world.
    public static World build(HashMap hash) throws WorldIsMissingDataException {
        try {
            return WorldBuilder.create()
                    .hex(hash.get("Hex").toString())
                    .sectorShortname(hash.get("SectorAbbreviation").toString())
                    .bases(hash.get("Bases").toString())
                    .name(hash.get("Name").toString())
                    .uwp(hash.get("UWP").toString())
                    .remarks(hash.get("Remarks").toString())
                    .worldX(Integer.parseInt(hash.get("WorldX").toString()))
                    .worldY(Integer.parseInt(hash.get("WorldY").toString()))
                    .build();
        } catch (NullPointerException npe) {
            throw new WorldIsMissingDataException();
        }
    }

    public static World buildMinimal(HashMap hash) {
        return WorldBuilder.create()
                .hex(hash.get("Hex").toString())
                .sectorShortname(hash.get("SectorAbbreviation").toString())
                .build();
    }

    public static World BuildRegina() // TODO: move to a factory
    {
        return WorldBuilder.create()
                .name("Regina")
                .hex("1910")
                .sectorShortname("spin")
                .remarks("Ri Pa Ph An Cp")
                .uwp("A788899-C")
                .worldX(-110)
                .worldY(-71)
                .build();
    }

    public static WorldBuilder create() {
        WorldBuilder builder = new WorldBuilder();
        builder.world = new World();
        return builder;
    }

    public World build() {
        world.createDay = (new Date()).getTime() / (24 * 60 * 60 * 1000); // to the day
        return world;
    }

    /**
     * Builder methods
     **/

    public WorldBuilder name(String name) {
        world.name = name;
        return this;
    }

    public WorldBuilder hex(String hex) {
        world.hex = hex;
        return this;
    }

    public WorldBuilder sectorShortname(String sec) {
        world.sectorAbbreviation = sec;
        return this;
    }

    public WorldBuilder uwp(String uwp) {
        world.uwp = uwp;
        return this;
    }

    public WorldBuilder remarks(String rem) {
        world.remarks = rem;
        return this;
    }

    public WorldBuilder bases(String bases) {
        world.bases = bases;
        return this;
    }

    public WorldBuilder worldX(int worldx) {
        world.worldX = worldx;
        return this;
    }

    public WorldBuilder worldY(int worldy) {
        world.worldY = worldy;
        return this;
    }
}
