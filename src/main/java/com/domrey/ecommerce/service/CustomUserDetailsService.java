package com.domrey.ecommerce.service;

import com.domrey.ecommerce.entity.MyUser;
import com.domrey.ecommerce.entity.Role;
import com.domrey.ecommerce.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUser = userRepository.findByUsername(username);
        if (myUser.isPresent()) {
            var userObj = myUser.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private String[] getRoles(MyUser objUser) {
        if (objUser.getRoles().toArray().length == 0) {
            return new String[]{"USER"};
        }

        // Convert to List<String>
//        List<String> roleNames = objUser.getRole().stream()
//                .map(Role::getName)
//                .collect(Collectors.toList());

        // Or convert directly to String[]

        return objUser.getRoles().stream()
                .map(Role::getName)
                .toArray(String[]::new); //bjUser.getRoles().split(",");

    }

}
