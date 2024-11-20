package com.example.api_resful.service;

import com.example.api_resful.dto.UserDTO;
import com.example.api_resful.entity.User;
import com.example.api_resful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return convertToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByUsername(userDTO.getUsername());
        if (userOpt.isPresent()) {
            throw new RuntimeException("Usuário já existe.");
        }

        User user = convertToUser(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setRole(userDTO.getRole());

        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        return user;
    }
}

