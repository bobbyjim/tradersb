package net.eagle.tas.tradersb.world;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Not just a plain old Data Object.  Originally it was, but it will also compute the distance,
 * in parsecs, from another world, create an array of trade codes from the Remarks list, and
 * decode the population and TL digits for you.
 * <p>
 * Created by eagro02 on 10/16/2017.
 */
public class World implements Serializable {
    public long createDay; // epoch

    public String name;
    public String hex;
    public String sectorAbbreviation;
    public String uwp;
    public String bases;
    public String remarks;
    public int worldX;
    public int worldY;

    private final String[] tcList = {"Ag", "As", "Ba", "De", "Fl", "Hi", "Ic", "In", "Lo", "Na", "Ni", "Po", "Ri", "Va"};

    public int distanceTo(World otherWorld) {
        int dx = worldX - otherWorld.worldX;
        int dy = worldY - otherWorld.worldY;
        int adx = Math.abs(dx);
        int ody = dy + Math.floorDiv(dy, 2);
        int sub = adx - ody;

        if ((worldX % 2 == 0) && (otherWorld.worldX % 2 != 0)) {
            ody += 1;
        }

        if (sub > adx && sub > ody) return sub;
        if (adx > ody) return adx;
        return ody;

/*        int row1 = worldY;
        int row2 = otherWorld.worldY;
        int col1 = worldX;
        int col2 = otherWorld.worldX;

        int a1 = row1 + ( col1 / 2 );
        int a2 = row2 + ( col2 / 2 );

        int d1 = Math.abs( a1 - a2 );
        int d2 = Math.abs( col1 - col2 );
        int d3 = Math.abs( (a1 - col1) - (a2 - col2) );

        // return the largest value from among d1, d2, d3.

        if ( d1 > d2 && d2 > d3 ) return d1;
        if ( d2 > d1 && d1 > d3 ) return d2;
        return d3; // last man standing*/
    }

    public String[] splitRemarks() {
        return remarks.split("\\s+");
    }

    public String[] tradeCodes() {
        ArrayList<String> list = new ArrayList<>();
        for (String tc : tcList) {
            if (remarks.contains(tc)) list.add(tc);
        }
        String[] outtype = new String[1];
        return list.toArray(outtype);
    }

    public int popDigit() {
        String pop = uwp.substring(4, 5);
        return eHex(pop);
    }

    public int TL() {
        String tl = uwp.substring(8, 9);
        return eHex(tl);
    }

    private int eHex(String val) {
        switch (val) {
            default:
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "A":
                return 10;
            case "B":
                return 11;
            case "C":
                return 12;
            case "D":
                return 13;
            case "E":
                return 14;
            case "F":
                return 15;
        }
    }

    public String toString() {
        return sectorAbbreviation + " "
                + hex + " "
                + String.format("%15s", name) + " "
                + uwp + " "
                + String.format("%2s", bases) + " "
                + remarks;
    }
}
