package net.eagle.tas.tradersb.player;

import net.eagle.tas.tradersb.world.World;

import java.io.Console;

/**
 * A player class exclusively for CLI use.
 */
public class CLI extends Player implements Playable {
    public void jump(World[] worlds) {
        if (worlds == null) {
            System.out.println("No data.");
            return;
        }

        System.out.println(printDestinations(worlds));
        selectDestination(worlds);
        System.out.println(loadShip());
    }

    private void selectDestination(World[] worlds) {
        Console console = System.console();
        int selection = -1;
        while (selection == -1) {
            System.out.println("Enter destination # (q = quit): ");
            try {
                String input = console.readLine();

                if ("q".equals(input))
                    System.exit(0);

                selection = Integer.parseInt(input);
                if (selection < 0 || selection >= worlds.length)
                    selection = -1;
            } catch (Exception e) {
                System.err.println("ERROR: process does not have a console for CLI input!  Quitting.");
                System.exit(0);
            }
        }

        this.world = worlds[selection];
    }
}
