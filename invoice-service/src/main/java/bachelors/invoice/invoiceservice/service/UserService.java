package bachelors.invoice.invoiceservice.service;

import bachelors.invoice.invoiceservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById();

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
