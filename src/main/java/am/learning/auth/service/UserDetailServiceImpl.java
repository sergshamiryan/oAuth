package am.learning.auth.service;

import am.learning.auth.model.Authority;
import am.learning.auth.model.Status;
import am.learning.auth.model.User;
import am.learning.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Primary
@Component
public class UserDetailServiceImpl  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("There is no user with this username");
        } else if (!user.getStatus().equals(Status.ACTIVE)) {
            throw new UsernameNotFoundException("Your account is not active");
        }


        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);

     }
}
