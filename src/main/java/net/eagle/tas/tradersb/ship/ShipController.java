package net.eagle.tas.tradersb.ship;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ships")
@Api(value = "ships", description=" ", tags={ "Ships" })
public class ShipController {
    @Autowired
    private ShipDAOService service;

    // GET /ships
    @ApiOperation(value = "Get all ships", notes = "", response = Ship.class, tags={ "Ships"} )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Returns a list of all ships", response = Ship.class)
    })
    @GetMapping()
    public List<Ship> getAllShips() {
        return service.getAllShips();
    }


    // GET /ships/$id
    @ApiOperation(value = "Get a ship by ID", notes="", response=Ship.class, tags={ "Ships" } )
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Returns ship data", response=Ship.class)
    })
    @GetMapping("{id}")
    public ResponseEntity<Ship> getShipById(@Valid @PathVariable String id) {
        try {
            return new ResponseEntity<>(service.getShip(id), HttpStatus.OK);
        } catch (ShipNotFoundException snfe) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(snfe.getHeaderName(), snfe.getLocalizedMessage());
            return new ResponseEntity<>(headers, snfe.getErrorCode());
        }
    }

    // PUT /ships
    @ApiOperation(value="Update a player's ship", notes="", response=Ship.class, tags={"Ships"})
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Ship Updated", response=Ship.class)
    })
    @PutMapping("{id}")
    public ResponseEntity<Ship> updateShip(@Valid @PathVariable String id, @RequestBody Ship params) {
        try {
            return new ResponseEntity<>(service.updateShip(id, params), HttpStatus.OK);
        } catch (CannotUpdateShipException sue) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(sue.getHeaderName(), sue.getLocalizedMessage());
            return new ResponseEntity<>(headers, sue.getErrorCode());
        }
    }

    // POST /ships/$id
}
