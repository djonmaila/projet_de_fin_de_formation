package com.formation.pharmacy_manager.dto.distributorDto;

import com.formation.pharmacy_manager.entities.Distributor;
// import com.formation.pharmacy_manager.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class DistributorRequestDto {

    private String userName;

    private String phoneNumber;

    private String email;

    private String password;

    private String distributorReference;

    private String role;

    public Distributor toDistributor(DistributorRequestDto dto){
        Distributor distributor = new Distributor();
        distributor.setUserName(dto.getUserName());
        distributor.setEmail(dto.getEmail());
        distributor.setPhoneNumber(dto.getPhoneNumber());
        distributor.setPassword(dto.getPassword());
        distributor.setDistributorReference(dto.getDistributorReference());
        distributor.setCreation_date(LocalDate.now());
        distributor.setUpdate_date(new Date());
        return distributor;
    }
}
