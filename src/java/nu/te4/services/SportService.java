/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.services;

import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.beans.SportsBean;
import nu.te4.support.User;

/**
 *
 * @author daca97002
 */
@Path("/")
public class SportService {

    @EJB
    SportsBean sportsbean;

    /**
     *
     * @param httpHeaders login:information in basic64 format
     * @return http-status 401 if fail to authoricate
     * http-status 500:servererror if fail to retrive data from 
     * else, if success: http-status 200: ok
     */
    @GET
    @Path("games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames(@Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        JsonArray data = sportsbean.getGames();
        if (data == null) {
            return Response.serverError().build();
        }
        return Response.ok(data).build();
    }

    /**
     *
     * @param body JsonObject containing information about game, see EAPIS
     * @param httpHeaders login:information in basic64 format
     * @return http-status 401 if fail to authoricate
     * http-status 400: Bad Request if fail in connection/querry/request
     * http-status 201: Created if success
     */
    @POST
    @Path("game")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addGame(String body, @Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        if (!sportsbean.addGame(body)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     *
     * @param id id of game wished to delete
     * @param httpHeaders login:information in basic64 format
     * @return http-status 401 if fail to authoricate
     * http-status 400: Bad Request if bad request
     * http-status 200:ok if success.
     */
    @DELETE
    @Path("game/{id}")
    public Response deleteGame(@PathParam("id") int id, @Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        if (!sportsbean.deleteGame(id)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    /**
     *
     * @param body information in JsonFormat (see EAPIS)
     * @param httpHeaders login:information in basic64 format
     * @return http-status 401 if fail to authoricate
     * http-status 400: Bad Request if bad request
     * http-status 201: Created if success, created in database
     */
    @PUT
    @Path("game")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeGame(String body, @Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        if (!sportsbean.changeGame(body)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     *
     * @return http-status 500: if server/database problem
     * http-status 200: ok + data if ok (see EAPIS for data format)
     */
    @GET
    @Path("table")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTable() {
        JsonArray data = sportsbean.getTable();
        if (data == null) {
            return Response.serverError().build();
        }
        return Response.ok(data).build();
    }

    /**
     *
     * @return http-status 500 if server/database error, http-status 200: ok + 
     * data if ok(see EAPIS for dataformat)
     */
    @GET
    @Path("teams")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeams() {

        JsonArray data = sportsbean.getTeams();
        if (data == null) {
            return Response.serverError().build();
        }
        return Response.ok(data).build();
    }

    /**
     *
     * @param id id of team requestead
     * @return http-status 500 if server/database error, http-status 200: ok + 
     * data if ok(see EAPIS for dataformat)
     */
    @GET
    @Path("team/{id}")
    public Response getTeam(@PathParam("id") int id) {
       
        JsonArray data = sportsbean.getTeam(id);
        if (data == null) {
            return Response.serverError().build();
        }
        return Response.ok(data).build();
    }
}
