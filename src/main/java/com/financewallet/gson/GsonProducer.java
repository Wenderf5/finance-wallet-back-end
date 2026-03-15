package com.financewallet.gson;

import com.google.gson.Gson;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class GsonProducer {
    @Produces
    public Gson gsonProducer(){
        return new Gson();
    }
}
