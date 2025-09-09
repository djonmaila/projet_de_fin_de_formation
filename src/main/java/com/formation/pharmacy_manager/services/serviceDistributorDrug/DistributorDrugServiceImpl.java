package com.formation.pharmacy_manager.services.serviceDistributorDrug;

import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugDequestDto;
import com.formation.pharmacy_manager.entities.DistributorDrug;
// import com.formation.pharmacy_manager.entities.Drug;
import com.formation.pharmacy_manager.repository.DistributorDrugRepository;
import com.formation.pharmacy_manager.repository.DistributorRepository;
import com.formation.pharmacy_manager.repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DistributorDrugServiceImpl implements DistributorDrugService{
    private DistributorDrugRepository distributorDrugRepository;
    private DistributorRepository distributorRepository;
    private DrugRepository drugRepository;
    @Override
    public DistributorDrug create(DistributorDrugDequestDto dto) {
        DistributorDrug dis = dto.toDistributorDrug(dto);
        dis.setDistributor(distributorRepository.findDistinctByUserName(dto.getUserName()));
        dis.setDrug(drugRepository.findDistinctByDrugName(dto.getDrugName()));

        return distributorDrugRepository.save(dis);
    }

}
