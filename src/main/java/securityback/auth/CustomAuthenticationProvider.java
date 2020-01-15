package securityback.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import securityback.database.UserRepository;
import securityback.entities.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUsername(username);

        if(user == null) {
            return null;
        }

        System.out.println(username);
        System.out.println(password);
        //System.out.println(user.getAuthorities());
        /*user.getAuthorities().forEach(e -> {
            System.out.println(e.getAuthority());
        });*/

        if(user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
