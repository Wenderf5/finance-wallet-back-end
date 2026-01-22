package com.financewallet.controllers;

import com.financewallet.DTOs.UserDTO;
import com.financewallet.services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserController {
    @Inject
    private UserService userService;
    private Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid UserDTO body) {
        this.userService.createUser(body);

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("status", Response.Status.CREATED.getStatusCode());
        responseJson.addProperty("message", "User created successfully.");
        String responseJsonString = this.gson.toJson(responseJson);

        return Response
                .status(Response.Status.CREATED)
                .entity(responseJsonString)
                .build();
    }
}
