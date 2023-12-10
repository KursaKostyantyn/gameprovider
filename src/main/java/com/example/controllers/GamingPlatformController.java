package com.example.controllers;

import com.example.models.dto.AuthDto;
import com.example.models.dto.GamingPlatformDto;
import com.example.services.api.GamingPlatformService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(("/gamingPlatforms"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamingPlatformController {
    @Inject
    GamingPlatformService gamingPlatformService;

    @POST
    @Transactional
    public Response create(@Valid GamingPlatformDto gamingPlatformDto) {
        return gamingPlatformService.create(gamingPlatformDto);
    }

    @POST
    @PermitAll
    @Path("/sign-in")
    public Response gamingPlatformLogin(@Valid AuthDto authDto) throws Exception {
        return gamingPlatformService.gamingPlatformLogin(authDto);
    }

    @GET
    @Path("/redirectToTheGame")
    @RolesAllowed("ADMIN")
    public Response redirectToTheGame(@QueryParam("userNickName") String userNickName,
                                      @QueryParam("userBalance") int userBalance,
                                      @QueryParam("gameId") int gameId) {
        return gamingPlatformService.redirectToTheGame(userNickName, userBalance, gameId);
    }

}
