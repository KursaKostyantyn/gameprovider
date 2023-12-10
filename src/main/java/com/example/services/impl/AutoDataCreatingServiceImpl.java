package com.example.services.impl;

import com.example.constants.Role;
import com.example.mappers.GameMapper;
import com.example.mappers.GamingPlatformMapper;
import com.example.models.dto.GameDto;
import com.example.models.dto.GamingPlatformDto;
import com.example.models.entity.Game;
import com.example.models.entity.GamingPlatform;
import com.example.repository.GameRepository;
import com.example.repository.GamingPlatformRepository;
import com.example.services.api.AutoDataCreatingService;
import com.example.utils.PasswordEncoderUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class AutoDataCreatingServiceImpl implements AutoDataCreatingService {
    @Inject
    GameRepository gameRepository;
    @Inject
    GamingPlatformRepository gamingPlatformRepository;

    @Override
    public Response create() {
        gameRepository.persist(GameMapper.convertGameDtoToGame(new GameDto("Game 1")));
        gameRepository.persist(GameMapper.convertGameDtoToGame(new GameDto("Game 2")));
        GamingPlatform gamingPlatform = GamingPlatformMapper.convertGamingPlatformDtoToGamingPlatform(new GamingPlatformDto("Gaming Platform"));
        List<Game> games=new ArrayList<>();
        games.add(gameRepository.findByName("Game 1"));
        gamingPlatform.setAllowedGames(games);
        gamingPlatform.setPassword(PasswordEncoderUtil.securePassword("admin"));
        gamingPlatform.setRole(Set.of(Role.ADMIN));
        gamingPlatformRepository.persist(gamingPlatform);

        return Response.status(Response.Status.CREATED).build();
    }
}
