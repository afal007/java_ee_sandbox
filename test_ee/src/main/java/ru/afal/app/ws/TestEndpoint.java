package ru.afal.app.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path( "/" )
public class TestEndpoint {

    @Context
    private UriInfo context;

    @GET
    public Response getMethod() {
        String response = "Hello World!";
        return Response.ok().entity( response ).build();
    }
}
