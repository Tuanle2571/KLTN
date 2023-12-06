package com.duan.demo01.security;

import com.duan.demo01.models.UserEntity;
import com.duan.demo01.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepo userRepository;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepository = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByUsername(username);
        if (userEntity != null) {
            User authUserEntity = new User(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())

            );
            return authUserEntity;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
