package net.eagle.tas.tradersb;

import net.eagle.tas.tradersb.ship.Ship;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TraderSBApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetPlayer_noID() {
        String id = "1";

        ResponseEntity<List> rsp = restTemplate.getForEntity("/players/" + id, List.class);
        Assert.assertNotNull(rsp);
        Assert.assertEquals(rsp.getStatusCode(), HttpStatus.NOT_FOUND);
        Object body = rsp.getBody();
        Assert.assertEquals(null, body);
    }

    @Test
    public void testGetShip() {
        String id = "99";

        ResponseEntity<List> rsp = restTemplate.getForEntity("/ships", List.class);
        Assert.assertNotNull(rsp);
        List<LinkedHashMap> list = rsp.getBody();
        Assert.assertNotEquals(0, list.size());

        // find the one we created
        Optional<LinkedHashMap> found = list.stream().filter(s -> id.equals(s.get("id"))).findFirst();
        Assert.assertEquals(true, found.isPresent());
    }

	/*@Test
    public void testUpdateShip()
    {
        ResponseEntity<Ship> rsp = restTemplate.getForEntity("/ships/99", Ship.class );
        Assert.assertNotNull(rsp);
        Assert.assertEquals(rsp.getStatusCode(), HttpStatus.OK);
        Ship ship = rsp.getBody();
        Assert.assertNotNull(ship);

        ship.setName( "Foobar" );
//        ResponseEntity<Ship> put_rsp = restTemplate.exchange("/ships/10203",
        restTemplate.put( "/ships/99", ship );

        ResponseEntity<Ship> rsp2 = restTemplate.getForEntity("/ships/99", Ship.class );
        Assert.assertNotNull(rsp2);
        Assert.assertEquals(rsp2.getStatusCode(), HttpStatus.OK);

        Ship ship2 = rsp2.getBody();
        Assert.assertNotNull(ship2);
        Assert.assertEquals(ship2.getName(), "Foobar");
    }*/

    @Test
    public void testUpdateShip_bad() {
        Ship ship = new Ship();
        restTemplate.put("/ships/10204", ship);

        ResponseEntity<Ship> rsp2 = restTemplate.getForEntity("/ships/10204", Ship.class);
        Assert.assertNotNull(rsp2);
        Assert.assertEquals(rsp2.getStatusCode(), HttpStatus.NOT_FOUND);

        Ship ship2 = rsp2.getBody();
        Assert.assertNull(ship2);
        //Assert.assertEquals(ship2.getName(), null);
    }
}
