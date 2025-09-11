package com.formation.pharmacy_manager.services.serviceDistributor;
import com.formation.pharmacy_manager.dto.distributorDto.DistributorRequestDto;
import com.formation.pharmacy_manager.dto.distributorDto.DistributorResponseDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.entities.Distributor;
import com.formation.pharmacy_manager.entities.Role;
import com.formation.pharmacy_manager.enumEntities.Type;
import com.formation.pharmacy_manager.repository.DistributorRepository;
import com.formation.pharmacy_manager.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class DistributorServiceImpl implements DistributorService {
    private DistributorRepository distributorRepository;
    private RoleRepository roleRepository;
    @Override
    public DistributorResponseDto create(DistributorRequestDto dto) {
        Distributor distributor = dto.toDistributor(dto);
        Role role = roleRepository.getByType(Type.valueOf(dto.getRole()));
        distributor.getRoles().add(role);

        Distributor dis = distributorRepository.save(distributor);
        return new DistributorResponseDto(
               dis.getUserId(),
               dis.getUserName(),
               dis.getPhoneNumber(),
               dis.getEmail(),
               dis.getDistributorReference(),
               dis.getCreation_date(),
               dis.getUpdate_date()
        );
    }

    @Override
    public List<DistributorResponseDto> getAllDistributor() {
        return distributorRepository.findAll().stream().map(
                dis -> new DistributorResponseDto(
                        dis.getUserId(),
                        dis.getUserName(),
                        dis.getPhoneNumber(),
                        dis.getEmail(),
                        dis.getDistributorReference(),
                        dis.getCreation_date(),
                        dis.getUpdate_date()
                )).toList();
    }

    @Override
    public DistributorResponseDto getById(long id) {
        Distributor dis = distributorRepository.findById(id).get();
        if (dis == null) throw  new RuntimeException("impossible : distributor not found");

        return new DistributorResponseDto(
                dis.getUserId(),
                dis.getUserName(),
                dis.getPhoneNumber(),
                dis.getEmail(),
                dis.getDistributorReference(),
                dis.getCreation_date(),
                dis.getUpdate_date()
        );
    }

    @Override
    public String deleteById(long id) {
        Distributor distributor = distributorRepository.findById(id).get();
        if (distributor == null) throw new RuntimeException("suppression impossible : distributor not found");
        distributorRepository.deleteById(id);
        return "distributor has sucessfull deleting";
    }

    @Override
    public DistributorResponseDto updateDistributor(long id, DistributorRequestDto dto) {
        Distributor distributor = distributorRepository.findById(id).get();
        if (distributor == null) throw new RuntimeException("suppression impossible : distributor not found");

        distributor.setUserName(dto.getUserName());
        distributor.setEmail(dto.getEmail());
        distributor.setPhoneNumber(dto.getPhoneNumber());
        distributor.setPassword(dto.getPassword());
        distributor.setDistributorReference(dto.getDistributorReference());
        distributor.setUpdate_date(new Date());

        Distributor dis  = distributorRepository.save(distributor);

        return new DistributorResponseDto(
                dis.getUserId(),
                dis.getUserName(),
                dis.getPhoneNumber(),
                dis.getEmail(),
                dis.getDistributorReference(),
                dis.getCreation_date(),
                dis.getUpdate_date()
        );
    }

    @Override
    public List<DrugResponseDto> getDrugFromDistributor(String name) {
        return distributorRepository.getDrugFromDistributor(name).stream().map(
                dg ->new DrugResponseDto(
                        dg.getDrugId(),
                        dg.getDrugName(),
                        dg.getDrugDescription(),
                        dg.getPeremption(),
                        dg.getPrice(),
                        dg.getCategory().getCategoryType(),
                        dg.getCreation_date(),
                        dg.getUpdate_date()
                )).toList();
    }

    @Override
    public DistributorResponseDto findDistinctByEmail(String email) {
        Distributor dis = distributorRepository.findDistinctByEmail(email);
        return new DistributorResponseDto(
                dis.getUserId(),
                dis.getUserName(),
                dis.getPhoneNumber(),
                dis.getEmail(),
                dis.getDistributorReference(),
                dis.getCreation_date(),
                dis.getUpdate_date()
        );
    }
}
