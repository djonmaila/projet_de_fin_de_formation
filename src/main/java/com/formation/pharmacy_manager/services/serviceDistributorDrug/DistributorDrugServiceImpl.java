package com.formation.pharmacy_manager.services.serviceDistributorDrug;

import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugDequestDto;
import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugResponseDto;
import com.formation.pharmacy_manager.entities.DistributorDrug;
import com.formation.pharmacy_manager.repository.DistributorDrugRepository;
import com.formation.pharmacy_manager.repository.DistributorRepository;
import com.formation.pharmacy_manager.repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DistributorDrugServiceImpl implements DistributorDrugService{
    private DistributorDrugRepository distributorDrugRepository;
    private DistributorRepository distributorRepository;
    private DrugRepository drugRepository;
    @Override
    public DistributorDrugResponseDto create(DistributorDrugDequestDto dto) {
        DistributorDrug dist =new DistributorDrug();
        dist.setQte(dto.getQte());
        dist.setCreation_date(new Date());
        dist.setUpdate_date(new Date());
        dist.setDistributor(distributorRepository.findDistinctByUserName(dto.getUserName()));
        dist.setDrug(drugRepository.findDistinctByDrugName(dto.getDrugName()));

        DistributorDrug dis =distributorDrugRepository.save(dist);

        return new DistributorDrugResponseDto(
                dis.getDistributorDrugId(),
                dis.getDistributor().getUserName(),
                dis.getDrug().getDrugName(),
                dis.getQte(),
                dis.getCreation_date(),
                dis.getUpdate_date()
        );
    }

    @Override
    public DistributorDrugResponseDto getByUserNameAndDrugName(String drugName, String userName) {
        DistributorDrug dis =  distributorDrugRepository.getByUserNameAndDrugName(drugName,userName);

        if(dis == null) throw new RuntimeException("no line shark found");

        return new DistributorDrugResponseDto(
                dis.getDistributorDrugId(),
                dis.getDistributor().getUserName(),
                dis.getDrug().getDrugName(),
                dis.getQte(),
                dis.getCreation_date(),
                dis.getUpdate_date()
        );
    }

    @Override
    public String deleteById(long id) {
        if(existById(id)){
            distributorDrugRepository.deleteById(id);
            return "line shark was deleting successfully";
        }
        return "no line shark found";
    }

    @Override
    public boolean existById(long id) {
        return distributorDrugRepository.existsById(id);
    }

    @Override
    public DistributorDrugResponseDto getById(long id) {
        return distributorDrugRepository.findById(id).map(
                dis->new DistributorDrugResponseDto(
                        dis.getDistributorDrugId(),
                        dis.getDistributor().getUserName(),
                        dis.getDrug().getDrugName(),
                        dis.getQte(),
                        dis.getCreation_date(),
                        dis.getUpdate_date()
                )
        ).orElse(null);
    }

    @Override
    public List<DistributorDrugResponseDto> getAllLineDisDrug() {
        return distributorDrugRepository.findAll().stream().map(
                dis->new DistributorDrugResponseDto(
                        dis.getDistributorDrugId(),
                        dis.getDistributor().getUserName(),
                        dis.getDrug().getDrugName(),
                        dis.getQte(),
                        dis.getCreation_date(),
                        dis.getUpdate_date()
                )
        ).toList();
    }

}
