/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.support.User;

@Path("/")
public class SportService {

    @GET
    @Path("games")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getGames(@Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        return Response.ok("Det fungerar").build();
    }
}
