package com.example.services.impl;

import com.example.mappers.GamingPlatformMapper;
import com.example.models.dto.AuthDto;
import com.example.models.dto.AuthenticationResponse;
import com.example.models.dto.GamingPlatformDto;
import com.example.models.entity.GamingPlatform;
import com.example.repository.GamingPlatformRepository;
import com.example.services.api.GamingPlatformService;
import com.example.utils.PasswordEncoderUtil;
import com.example.utils.TokenUtils;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class GamingPlatformServiceImpl implements GamingPlatformService {

    private final long expirationTimeForAccessToken = 18000;
    private final String issuer = "https://example.com";

    @Inject
    GamingPlatformRepository gamingPlatformRepository;
    @Inject
    SecurityContext securityContext;

    @Override
    public Response create(GamingPlatformDto gamingPlatformDto) {
        GamingPlatform gamingPlatform = GamingPlatformMapper.convertGamingPlatformDtoToGamingPlatform(gamingPlatformDto);
        gamingPlatformRepository.persist(gamingPlatform);
        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    public Response gamingPlatformLogin(AuthDto authDto) throws Exception {
        GamingPlatform gamingPlatform = gamingPlatformRepository.findByName(authDto.getLogin());

        if (gamingPlatform!=null && PasswordEncoderUtil.validatePassword(gamingPlatform.getPassword(), authDto.getPassword())) {
            return Response.ok(new AuthenticationResponse(TokenUtils.generateToken(gamingPlatform.getName(), gamingPlatform.getRole(), expirationTimeForAccessToken, issuer))).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
    }

    @Override
    public Response redirectToTheGame(String userNickName, int userBalance, int gameId) {
        JWTCallerPrincipal jwtCallerPrincipal = (JWTCallerPrincipal) securityContext.getUserPrincipal();
        String gamingPlatformName = jwtCallerPrincipal.getName();
        GamingPlatform gamingPlatform = gamingPlatformRepository.findByName(gamingPlatformName);
        if (gamingPlatform==null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (!checkAllowedGames(gameId, gamingPlatform)) {
            return Response.status(Response.Status.FORBIDDEN).entity("This game is not allowed").build();
        }
        return Response.status(Response.Status.OK).entity("User " + userNickName + " logged in").build();
    }

    private boolean checkAllowedGames(int gameId, GamingPlatform gamingPlatform) {
        return gamingPlatform.getAllowedGames().stream().anyMatch(game -> game.getId() == gameId);
    }
}
