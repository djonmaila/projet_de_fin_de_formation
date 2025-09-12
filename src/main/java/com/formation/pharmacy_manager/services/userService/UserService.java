package com.formation.pharmacy_manager.services.userService;

import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;
import com.formation.pharmacy_manager.dto.userDto.UserRoleRequestDto;

import java.util.List;

public interface UserService {
    void addRoleToUser(UserRoleRequestDto dto);
    void removeRoleToUser(UserRoleRequestDto dto);
    List<RoleResponseDto> getRolesForUser(String email);
}
