package com.example.services.impl;

import com.example.mappers.GameMapper;
import com.example.models.dto.GameDto;
import com.example.models.entity.Game;
import com.example.repository.GameRepository;
import com.example.services.api.GameService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class GameServiceImpl implements GameService {
    @Inject
    GameRepository gameRepository;

    @Override
    public Response create(GameDto gameDto) {
        Game game = GameMapper.convertGameDtoToGame(gameDto);
        gameRepository.persist(game);

        return Response.status(Response.Status.CREATED).build();
    }
}
