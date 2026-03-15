package com.financewallet.exceptionMapper;

import com.financewallet.exceptions.InvalidEmailCodeException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidEmailCodeExceptionMapper implements ExceptionMapper<InvalidEmailCodeException> {
    @Inject
    private Gson gson;

    public Response toResponse(InvalidEmailCodeException ex) {
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("status", Response.Status.BAD_REQUEST.getStatusCode());
        responseJson.addProperty("message", ex.getMessage());
        String responseJsonString = this.gson.toJson(responseJson);

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(responseJsonString)
                .build();
    }
}
