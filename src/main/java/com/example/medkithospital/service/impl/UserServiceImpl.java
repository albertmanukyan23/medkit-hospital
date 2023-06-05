package com.example.medkithospital.service.impl;

import com.example.medkithospital.entity.User;
import com.example.medkithospital.entity.UserType;
import com.example.medkithospital.repository.UserRepository;
import com.example.medkithospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;


    private final UserRepository userRepository;
    @Override
    public void registerUser(User user) {
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setUserType(UserType.PATIENT);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }
}
