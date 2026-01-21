package com.financewallet.services;

import com.financewallet.DTOs.UserDTO;
import com.financewallet.dataBase.entities.UserEntity;
import com.financewallet.dataBase.entities.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Transactional
    public void createUser(UserDTO user) {
        UserEntity newUser = new UserEntity(
                user.getEmail(),
                user.getPassword(),
                user.getUserName(),
                user.getPhoto());

        this.userRepository.save(newUser);
    }
}
