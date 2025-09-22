package com.formation.pharmacy_manager.dto.pharmacistDto;

import com.formation.pharmacy_manager.dto.userDto.UserRequestDto;

public class PharmacistRequestDto extends UserRequestDto {
    public PharmacistRequestDto(String userName, String phoneNumber, String email, String password, String role) {
        super(userName, phoneNumber, email, password, role);
    }
}
