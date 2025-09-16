package com.formation.pharmacy_manager.services.userService;

import com.formation.pharmacy_manager.dto.commandeDto.CommandeResponseDto;
import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;
import com.formation.pharmacy_manager.dto.userDto.UserRoleRequestDto;
import com.formation.pharmacy_manager.entities.Command;

import java.util.List;

public interface UserService {
    void addRoleToUser(UserRoleRequestDto dto);
    void removeRoleToUser(UserRoleRequestDto dto);
    List<RoleResponseDto> getRolesForUser(String email);
    List<CommandeResponseDto> getCommandNotEmpty(String userName);
    List<CommandeResponseDto> getCommandEmpty(String userName);
}
