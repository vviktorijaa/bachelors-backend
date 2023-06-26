package bachelors.invoice.invoiceservice.web;

import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.model.dto.UserDTO;
import bachelors.invoice.invoiceservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userService.findAll();
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        if (this.userService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        return this.userService.create(userDTO)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
