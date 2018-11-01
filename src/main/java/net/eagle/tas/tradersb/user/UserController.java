package net.eagle.tas.tradersb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAOService service;

    // GET /users
    @GetMapping("")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /users/{id}
    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        User foundUser = service.findOne(id);
        if (foundUser == null)
            throw new UserNotFoundException("id-" + id);
        return foundUser;
    }

    // get all posts for a User
/*    @GetMapping("/users/{id}/posts")
    public String getAllPostsForUser(@PathVariable int id)
    {
        User user = getUser(id);
        if ( user != null )

    }
*/

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
    }

    // Create
    // input - details of user
    // output - CREATED + return the URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // CREATED: /user/{id}   ... savedUser.getId
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
