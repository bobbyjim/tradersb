package net.eagle.tas.tradersb.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {
    @Autowired
    private ShipDAOService service;

    // GET /ships
    @GetMapping()
    public List<Ship> getAllShips() {
        return service.getAllShips();
    }

    // GET /ships/$id
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
