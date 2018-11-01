package net.eagle.tas.tradersb.player;

import io.swagger.annotations.Api;
import net.eagle.tas.tradersb.exception.TraderException;
import net.eagle.tas.tradersb.ship.Ship;
import net.eagle.tas.tradersb.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/players")
@Api(value="Players", description="Player Management", tags={ "Player" })
public class PlayerController {
    @Autowired
    private PlayerDAOService playerService;

    @PostMapping
    public Player createPlayer() {
        return playerService.createPlayer();
    }

    @GetMapping("/")
    public ResponseEntity<List<Player>> getallPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

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

    @GetMapping("{id}/skills")
    public ResponseEntity<HashMap<String, Integer>> getSkills(@PathVariable String id) {
        return new ResponseEntity<>(getPlayer(id).getBody().getSkills(), HttpStatus.OK);
    }

    @PutMapping("{id}/skills/{skillname}")
    public ResponseEntity<HashMap<String, Integer>> improveSkill(@PathVariable String id, @PathVariable String skillname)
    {
        try {
            playerService.improveSkill(id, skillname);
            return getSkills(id);
        }
        catch( TraderException e )
        {
            HttpHeaders headers = new HttpHeaders();
            headers.set(e.getHeaderName(), e.getLocalizedMessage());
            return new ResponseEntity<>(headers, e.getErrorCode());
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
        try {
            return new ResponseEntity<>(playerService.updateShip(id, updatedShip), HttpStatus.ACCEPTED);
        } catch (TraderException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(e.getHeaderName(), e.getLocalizedMessage());
            return new ResponseEntity<>(headers, e.getErrorCode());
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
        try {
            return new ResponseEntity<>(playerService.jump(id, destination), HttpStatus.ACCEPTED);
        } catch (TraderException wimde) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(wimde.getHeaderName(), wimde.getLocalizedMessage());
            return new ResponseEntity<>(headers, wimde.getErrorCode());
        }
    }

    // GET /players/$id/cargo           see cargo available on this world

    // POST /players/$id/cargo          buy and load cargo

    // DELETE /players/$id/cargo/$cid   sell cargo on current world



    // POST /players/$id/freight        load freight

    // DELETE /players/$id/freight      unload freight



    // GET /players/$id/passengers      see H/M/L passengers available

    // POST /players/$id/passengers     take on passengers

    // DELETE /players/$id/passengers   deplane passengers

}
