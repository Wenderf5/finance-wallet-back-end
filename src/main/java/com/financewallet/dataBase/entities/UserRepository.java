package com.financewallet.dataBase.entities;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext(unitName = "financeWalletPU")
    private EntityManager entityManager;

    public void save(UserEntity user){
        this.entityManager.persist(user);
    }
}
