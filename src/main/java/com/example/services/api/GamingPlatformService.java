package com.example.services.api;

import com.example.models.dto.AuthDto;
import com.example.models.dto.GamingPlatformDto;
import jakarta.ws.rs.core.Response;

public interface GamingPlatformService {
    Response create(GamingPlatformDto gamingPlatformDto);

    Response gamingPlatformLogin(AuthDto authDto) throws Exception;
    Response redirectToTheGame(String userNickName, int userBalance, int gameId);

}
