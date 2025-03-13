package za.co.moson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.moson.exceptions.UserException;
import za.co.moson.models.User;
import za.co.moson.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserException {
        return ResponseEntity.ok(this.userService.create(user));
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException {
        return ResponseEntity.ok(this.userService.update(user));
    }

    @GetMapping("/refNumber/{refNumber}/")
    public ResponseEntity<User> updateUser(@PathVariable String refNumber) {
        return ResponseEntity.ok(this.userService.findUserByRefNumber(refNumber).orElse(null));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<User> updateUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findUserByID(id).orElse(null));
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseEntity.BodyBuilder> deleteUser(@RequestBody User user) {
        this.userService.delete(user);
        return ResponseEntity.ok(ResponseEntity.accepted());
    }
}
