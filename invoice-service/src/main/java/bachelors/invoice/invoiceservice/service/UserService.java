package bachelors.invoice.invoiceservice.service;

import bachelors.invoice.invoiceservice.model.User;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById();

    User findByEmail(String email);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
