package com.formation.pharmacy_manager.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserConnectResponse {
    private String token;
    private String username;
}
