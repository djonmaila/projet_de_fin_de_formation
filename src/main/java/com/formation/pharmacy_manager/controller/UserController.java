package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;
import com.formation.pharmacy_manager.dto.userDto.UserRoleRequestDto;
import com.formation.pharmacy_manager.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roleServiceUser")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PutMapping("/add")
    public ResponseEntity<String> addRoleToUser(@RequestBody UserRoleRequestDto dto){
        userService.addRoleToUser(dto);
        return new ResponseEntity<>("role is added to user", HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> removeRoleToUser(@RequestBody UserRoleRequestDto dto){
        userService.removeRoleToUser(dto);
        return new ResponseEntity<>("role is deleting to user",HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<RoleResponseDto>> getRolesToUser(@PathVariable String email){
        return new ResponseEntity<>(userService.getRolesForUser(email),HttpStatus.OK);
    }
}
