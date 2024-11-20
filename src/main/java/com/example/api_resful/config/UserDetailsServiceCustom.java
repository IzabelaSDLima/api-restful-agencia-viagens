package com.example.api_resful.config;

import com.example.api_resful.dto.UserDTO;
import com.example.api_resful.entity.User;
import com.example.api_resful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(AuthenticatedUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }
}
