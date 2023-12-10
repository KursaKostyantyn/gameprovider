package com.example.mappers;

import com.example.models.dto.GameDto;
import com.example.models.entity.Game;
import org.modelmapper.ModelMapper;

public class GameMapper {

    public static Game convertGameDtoToGame(GameDto gameDto){
        ModelMapper mapper =new ModelMapper();

        return mapper.map(gameDto,Game.class);
    }

    public static GameDto convertGameToGameDto (Game game){
        ModelMapper mapper =new ModelMapper();

        return mapper.map(game,GameDto.class);
    }
}
