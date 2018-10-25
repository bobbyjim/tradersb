package net.eagle.tas.tradersb.player;

import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.ship.ShipHasProblemException;
import net.eagle.tas.tradersb.world.World;
import net.eagle.tas.tradersb.world.WorldIsMissingDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerDAOService playerService;

    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayer(@Valid @PathVariable String id) {
        try {
            return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
        } catch (PlayerNotFoundException pnfe) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(pnfe.getHeaderName(), pnfe.getLocalizedMessage());
            return new ResponseEntity<>(headers, pnfe.getErrorCode());
        }
    }

    @GetMapping("{id}/world")
    public World getPlayerWorld(@PathVariable String id) {
        ResponseEntity<Player> rsp = getPlayer(id);
        return rsp.getBody().getWorld();
    }

    @GetMapping("{id}/ship")
    public Ship getPlayerShip(@PathVariable String id) {
        ResponseEntity<Player> rsp = getPlayer(id);
        return rsp.getBody().getShip();
    }

    @PutMapping("{id}/ship")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Ship> updatePlayerShip(@PathVariable String id, @RequestBody Ship updatedShip) {
        ResponseEntity<Player> rsp = getPlayer(id);
        Player player = rsp.getBody();
        try {
            playerService.updateShip(player, updatedShip);
            return new ResponseEntity<>(player.getShip(), HttpStatus.ACCEPTED);
        } catch (ShipHasProblemException suhpe)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.set(suhpe.getHeaderName(), suhpe.getLocalizedMessage());
            return new ResponseEntity<>(headers, suhpe.getErrorCode());
        }
    }

    @GetMapping("{id}/hex")
    public String getHex(@PathVariable String id) {
        ResponseEntity<Player> rsp = getPlayer(id);
        World world = rsp.getBody().getWorld();
        String json = "{ 'hex': '" + world.hex + "', 'sectorAbbreviation': '" + world.sectorAbbreviation + "' }";
        return json;
    }

    @PutMapping("{id}/world")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<World> jump(@PathVariable String id, @RequestBody World destination) {
        ResponseEntity<Player> rsp = getPlayer(id);
        Player player = rsp.getBody();
        try {
            playerService.jump(player, destination);
            return new ResponseEntity<>(player.getWorld(), HttpStatus.ACCEPTED);
        } catch (WorldIsMissingDataException wimde) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(wimde.getHeaderName(), wimde.getLocalizedMessage());
            return new ResponseEntity<>(headers, wimde.getErrorCode());
        }
    }

    @PostMapping
    public Player createPlayer() {
        return playerService.createPlayer();
    }

}
