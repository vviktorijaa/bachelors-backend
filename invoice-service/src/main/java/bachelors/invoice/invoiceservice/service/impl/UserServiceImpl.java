package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.repository.UserRepository;
import bachelors.invoice.invoiceservice.service.UserService;
import org.springframework.stereotype.Service;

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
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(email + " not found!"));
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword()).build();
//    }
//
//    private String encodePassword(String password) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder.encode(password);
//    }
}
