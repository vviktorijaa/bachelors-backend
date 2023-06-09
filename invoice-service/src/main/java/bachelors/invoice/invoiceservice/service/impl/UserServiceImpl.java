package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.exceptions.UsernameAlreadyExistsException;
import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.model.dto.UserDTO;
import bachelors.invoice.invoiceservice.repository.UserRepository;
import bachelors.invoice.invoiceservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById() {
        return this.userRepository.findById(1L);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        return user.get();
    }

    @Override
    public Optional<User> create(UserDTO userDTO) {
        User user = new User(userDTO.username(), userDTO.password(), new ArrayList<>());
        user.setPassword(encodePassword(user.getPassword()));
        if (userRepository.findByEmail(userDTO.username()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        } else {
            this.userRepository.save(user);
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = this.userRepository.findByEmailAndPassword(email, password);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(username);
        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()).build();
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}
