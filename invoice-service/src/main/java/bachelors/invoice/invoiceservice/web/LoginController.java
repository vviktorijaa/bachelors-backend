package bachelors.invoice.invoiceservice.web;

import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/login", produces="application/json")
    public ResponseEntity<User> login(Principal principal) {
        return ResponseEntity.ok(this.userService.findByEmail(principal.getName()));
    }
}
