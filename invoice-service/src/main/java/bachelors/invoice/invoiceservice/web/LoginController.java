package bachelors.invoice.invoiceservice.web;

import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.model.dto.LoginUserDTO;
import bachelors.invoice.invoiceservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/login")
    public ResponseEntity<User> login(@RequestBody LoginUserDTO loginUserDTO) {
        return ResponseEntity.ok(this.userService.findByEmail(loginUserDTO.username()));
    }
}
