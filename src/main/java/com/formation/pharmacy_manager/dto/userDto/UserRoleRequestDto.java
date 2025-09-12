package com.formation.pharmacy_manager.dto.userDto;

import com.formation.pharmacy_manager.enumEntities.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRoleRequestDto {
    Type type;
    String email;
}
