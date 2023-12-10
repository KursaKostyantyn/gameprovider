package com.example.mappers;

import com.example.models.dto.GamingPlatformDto;
import com.example.models.entity.GamingPlatform;
import org.modelmapper.ModelMapper;

public class GamingPlatformMapper {

    public static GamingPlatform convertGamingPlatformDtoToGamingPlatform(GamingPlatformDto gamingPlatformDto) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(gamingPlatformDto, GamingPlatform.class);
    }

    public static GamingPlatformDto convertGamingPlatformToGamingPlatformDto(GamingPlatform gamingPlatform) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(gamingPlatform, GamingPlatformDto.class);
    }
}
