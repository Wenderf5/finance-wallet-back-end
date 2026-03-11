package com.financewallet.services;

import com.financewallet.DTOs.CreateUserDTO;
import com.financewallet.dataBase.entities.UserEntity;
import com.financewallet.dataBase.repositories.UserRepository;
import com.financewallet.exceptions.EmailAlreadyExistException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository userRepository;

    public void generateCreateUserCode(CreateUserDTO user){
        
    }

    @Transactional
    public void createUser(CreateUserDTO user) {
        try {
            UserEntity newUser = new UserEntity(
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserName(),
                    "test-photo-link");

            this.userRepository.save(newUser);
        } catch (Exception e) {
            if (isConstraintViolation(e)) {
                throw new EmailAlreadyExistException("This email address is already in use.");
            }
            throw new RuntimeException("Error registering user.");
        }
    }

    private boolean isConstraintViolation(Throwable e) {
        Throwable cause = e;
        while (cause != null) {
            String className = cause.getClass().getSimpleName();
            String message = cause.getMessage() == null ? "" : cause.getMessage().toLowerCase();
            if (className.contains("ConstraintViolationException") ||
                className.contains("SQLIntegrityConstraintViolationException") ||
                message.contains("duplicate") || 
                message.contains("violates unique constraint")) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
