package com.formation.pharmacy_manager.services.serviceDistributor;

import com.formation.pharmacy_manager.dto.distributorDto.DistributorRequestDto;
import com.formation.pharmacy_manager.dto.distributorDto.DistributorResponseDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
// import com.formation.pharmacy_manager.entities.Distributor;
// import com.formation.pharmacy_manager.entities.Drug;
// import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistributorService {
    DistributorResponseDto create(DistributorRequestDto dto);
    List<DistributorResponseDto> getAllDistributor();
    DistributorResponseDto getById(long id);
    String deleteById(long id);
    DistributorResponseDto updateDistributor(long id , DistributorRequestDto dto);
    List<DrugResponseDto> getDrugFromDistributor(String name);
    DistributorResponseDto findDistinctByEmail(String email);
}
