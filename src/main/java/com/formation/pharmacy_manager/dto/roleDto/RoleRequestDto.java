package com.formation.pharmacy_manager.dto.roleDto;

import com.formation.pharmacy_manager.enumEntities.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoleRequestDto {
    private Type type;
}
