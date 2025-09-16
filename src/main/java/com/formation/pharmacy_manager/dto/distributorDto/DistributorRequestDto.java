package com.formation.pharmacy_manager.dto.distributorDto;

import com.formation.pharmacy_manager.dto.userDto.UserRequestDto;
import com.formation.pharmacy_manager.entities.Distributor;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class DistributorRequestDto extends UserRequestDto {

    private String distributorReference;

    public DistributorRequestDto(String userName, String phoneNumber, String email, String password, String distributorReference,String role) {
        super(userName, phoneNumber, email, password,role);
        this.distributorReference = distributorReference;
    }
}
