package com.formation.pharmacy_manager.services.roleService;

import com.formation.pharmacy_manager.dto.roleDto.RoleRequestDto;
import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;
import com.formation.pharmacy_manager.entities.Role;
import com.formation.pharmacy_manager.entities.User;
import com.formation.pharmacy_manager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;
    @Override
    public RoleResponseDto create(RoleRequestDto dto) {
        Role role = new Role();
        role.setType(dto.getType());

        Role r = roleRepository.save(role);
        return new RoleResponseDto(r.getType());
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll().stream().map(
                role -> new RoleResponseDto(
                        role.getType()
                )).toList();
    }

    @Override
    public String deleteById(long id) {
        Role role = roleRepository.findById(id).get();
        if (role == null) throw new RuntimeException("role not found");
        List<User> user = role.getUsers();
        for (User u : user){
            u.getRoles().remove(role);
        }
        roleRepository.deleteById(id);
        return "role deleting successfully";
    }

    @Override
    public boolean existById(long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public RoleResponseDto updateRole(long id, RoleRequestDto dto) {
        Role role = roleRepository.findById(id).orElseThrow();
        role.setType(dto.getType());

        Role r = roleRepository.save(role);
        return new RoleResponseDto(r.getType());
    }
}
