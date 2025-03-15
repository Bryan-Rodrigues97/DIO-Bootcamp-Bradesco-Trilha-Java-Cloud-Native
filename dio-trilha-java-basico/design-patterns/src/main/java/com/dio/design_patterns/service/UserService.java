package com.dio.design_patterns.service;

import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean validateUser(String usersname, String password) {
        User user = repository.findByUsername(usersname).orElseThrow(() -> new UsernameNotFoundException(""));
        return passwordEncoder.matches(password, user.getPassword());
    }
}
