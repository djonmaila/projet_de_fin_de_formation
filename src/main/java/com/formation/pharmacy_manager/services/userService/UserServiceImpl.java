package com.formation.pharmacy_manager.services.userService;

import com.formation.pharmacy_manager.dto.commandeDto.CommandeResponseDto;
import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;
import com.formation.pharmacy_manager.dto.userDto.UserRoleRequestDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.Role;
import com.formation.pharmacy_manager.entities.User;
import com.formation.pharmacy_manager.repository.RoleRepository;
import com.formation.pharmacy_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public void addRoleToUser(UserRoleRequestDto dto) {
        Role role = roleRepository.getByType(dto.getType());
        User user = userRepository.findDistinctByEmail(dto.getEmail());
        user.getRoles().add(role);

        userRepository.save(user);
    }

    public void removeRoleToUser(UserRoleRequestDto dto){
        Role role = roleRepository.getByType(dto.getType());
        User user = userRepository.findDistinctByEmail(dto.getEmail());
        user.getRoles().remove(role);

        userRepository.save(user);
    }

    public List<RoleResponseDto> getRolesForUser(String email){
        User user = userRepository.findDistinctByEmail(email);
        List<Role> role = user.getRoles();
        return role.stream().map(
                r->new RoleResponseDto(
                        r.getType()
                )).toList();
    }

    @Override
    public List<CommandeResponseDto> getCommandNotEmpty(String userName) {
        return userRepository.getCommandNotEmpty(userName).stream().map(
                cmd->new CommandeResponseDto(
                cmd.getCommandId(),
                cmd.getPseudo(),
                cmd.getUser().getUserName(),
                cmd.getCreation_date()
                )
        ).toList();
    }

    @Override
    public List<CommandeResponseDto> getCommandEmpty(String userName) {
        return userRepository.getCommandEmpty(userName).stream().map(
                cmd->new CommandeResponseDto(
                        cmd.getCommandId(),
                        cmd.getPseudo(),
                        cmd.getUser().getUserName(),
                        cmd.getCreation_date()
                )
        ).toList();
    }
}
