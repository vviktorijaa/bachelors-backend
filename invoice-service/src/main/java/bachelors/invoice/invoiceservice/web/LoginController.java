package bachelors.invoice.invoiceservice.web;

import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/loginUser")
    public User login(Principal principal) {
        return this.userService.findByEmail(principal.getName());
    }
}
