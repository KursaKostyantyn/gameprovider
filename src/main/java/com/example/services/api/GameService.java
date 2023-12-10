package com.example.services.api;

import com.example.models.dto.GameDto;
import jakarta.ws.rs.core.Response;

public interface GameService {

    Response create(GameDto gameDto);

}
