package com.formation.pharmacy_manager.services.roleService;

import com.formation.pharmacy_manager.dto.roleDto.RoleRequestDto;
import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;

import java.util.List;

public interface RoleService {
    RoleResponseDto create(RoleRequestDto dto);
    List<RoleResponseDto> getAllRoles();
    String deleteById(long id);
    boolean existById(long id);
    RoleResponseDto updateRole(long id,RoleRequestDto dto);
}
