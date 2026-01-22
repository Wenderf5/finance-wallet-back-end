package com.financewallet.services;

import java.util.Optional;

import com.financewallet.DTOs.UserDTO;
import com.financewallet.dataBase.entities.UserEntity;
import com.financewallet.dataBase.entities.UserRepository;
import com.financewallet.exceptions.EmailAlreadyExistException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository userRepository;

    @Transactional
    public void createUser(UserDTO user) {
        Optional<UserEntity> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyExistException("This email address is already in use.");
        }

        try {
            UserEntity newUser = new UserEntity(
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserName(),
                    user.getPhoto());

            this.userRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException("Error registering user.");
        }
    }
}
