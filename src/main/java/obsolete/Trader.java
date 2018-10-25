package obsolete;


import obsolete.client.TraderClient;
import obsolete.network.ExternalDataServer;
import obsolete.network.InternalDataServer;

/**
 * Trader main program 11/29/2017.
 */
public class Trader {
    public static void main(String[] args) throws Exception {
        String playerName = "Jamison";
        int internalPort = 2244;
        int externalPort = 7244;
        boolean cli = false;

        switch (args.length) {
            case 4:
                cli = "cli".equals(args[3]);
            case 3:
                externalPort = Integer.parseInt(args[2]);
            case 2:
                internalPort = Integer.parseInt(args[1]);
            case 1:
                playerName = args[0];
            default:
                break;
        }

        //
        //  Launch public ("read-only") and private ("read-write") servers.
        //
        new InternalDataServer(internalPort);
        new ExternalDataServer(externalPort);

        //
        //  Optional: Launch client using playerName
        //
        if (cli)
            new TraderClient(playerName).run();
        else // snooze forever
            while (true) {
                Thread.sleep(60 * 1000);
            }
    }
}
