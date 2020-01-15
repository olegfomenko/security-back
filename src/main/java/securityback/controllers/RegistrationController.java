package securityback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import securityback.database.UserRepository;
import securityback.entities.User;

import java.util.Collections;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    public String register(@RequestBody User user) {
        user.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return "Successful";
    }
}
