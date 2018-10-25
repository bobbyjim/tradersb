package obsolete.client;

import net.eagle.tas.tradersb.map.MapAccessible;
import net.eagle.tas.tradersb.map.MapFactory;
import net.eagle.tas.tradersb.player.Playable;
import net.eagle.tas.tradersb.player.PlayerFactory;
import net.eagle.tas.tradersb.world.World;

public class TraderClient {
    public Playable player;

    public TraderClient(Playable player) {
        this.player = player;
    }

    public TraderClient(String name) {
        player = PlayerFactory.getPlayer(name);
    }

    public World[] scan(Playable player) {
        MapAccessible api = MapFactory.getMapEngine("maps.TravellerMap");
        return api.getJumpMap(player.getWorld(), player.getWorld().sectorAbbreviation, player.getWorld().hex, player.getShip().getJump());
    }

    public void run() {
        while (true) {
            System.out.println(this.player.youAreHere());
            System.out.println(this.player.unloadShip());
            World[] worlds = this.scan(this.player);
            this.player.jump(worlds);
            PlayerFactory.savePlayer(this.player);
        }
    }

    public static void main(String[] args) throws Exception {
        String playerName = "Jamison";

        if (args.length > 0)
            playerName = args[0];

        TraderClient client = new TraderClient(playerName);

        client.run();
    }
}
