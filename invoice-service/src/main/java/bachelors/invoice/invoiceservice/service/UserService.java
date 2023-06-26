package bachelors.invoice.invoiceservice.service;

import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    Optional<User> findById();

    User findByEmail(String email);

    Optional<User> create(UserDTO userDTO);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
