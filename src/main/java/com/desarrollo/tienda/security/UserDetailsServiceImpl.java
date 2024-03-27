package com.desarrollo.tienda.security;

import com.desarrollo.tienda.entity.UserModel;
import com.desarrollo.tienda.repository.IUserRepository;
import com.desarrollo.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
   private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel users = this.userService.findByOneEmail(email);

        return new UserDetailsImpl(users);
    }
}
