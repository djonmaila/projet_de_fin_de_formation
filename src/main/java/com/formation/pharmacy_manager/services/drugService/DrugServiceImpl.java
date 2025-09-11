package com.formation.pharmacy_manager.services.drugService;

import com.formation.pharmacy_manager.dto.drugDto.DrugRequestDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.entities.CommandDrug;
import com.formation.pharmacy_manager.entities.DistributorDrug;
import com.formation.pharmacy_manager.entities.Drug;
import com.formation.pharmacy_manager.repository.CategoryRepository;
import com.formation.pharmacy_manager.repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DrugServiceImpl implements DrugService{
    private DrugRepository drugRepository;
    private CategoryRepository categoryRepository;
    @Override
    public DrugResponseDto createDrug(DrugRequestDto dto) {
        Drug drug = dto.toDrugEntity(dto);
        drug.setCategory(categoryRepository.findDistinctByCategoryType(dto.getType()));

        Drug dg = drugRepository.save(drug);
        return new DrugResponseDto(
                dg.getDrugId(),
                dg.getDrugName(),
                dg.getDrugDescription(),
                dg.getPeremption(),
                dg.getPrice(),
                dg.getCategory().getCategoryType(),
                dg.getCreation_date(),
                dg.getUpdate_date()
        );
    }

    public List<DrugResponseDto> getAllDrug(){
        return drugRepository.findAll().stream().map(
                dg -> new DrugResponseDto(
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
    public String deleteById(long id) {
        Drug drug = drugRepository.findById(id).get();
        if(drug == null) throw new RuntimeException("deleting is impossible : drug not found");
        List<CommandDrug> commandDrugList = drug.getCommandDrugList();
        for(CommandDrug dg : commandDrugList){
            dg.setDrug(null);
        }

        List<DistributorDrug> distributorDrugs = drug.getDistributorDrugList();
        for (DistributorDrug disDrug : distributorDrugs){
            disDrug.setDrug(null);
        }

        drugRepository.deleteById(id);
        return "the drug was successfull deleting";
    }

    @Override
    public boolean existById(long id) {
        return drugRepository.existsById(id);
    }

    @Override
    public DrugResponseDto updateDrug(long id, DrugRequestDto dto) {
        Drug drug = drugRepository.findById(id).get();
        if(drug == null) throw new RuntimeException("Updating is impossible : drug not found");
        drug.setDrugName(dto.getDrugName());
        drug.setDrugDescription(dto.getDrugDescription());
        drug.setPeremption(dto.getPeremption());
        drug.setPrice(dto.getPrice());
        drug.setUpdate_date(new Date());
        drug.setCategory(categoryRepository.findDistinctByCategoryType(dto.getType()));

        Drug dg = drugRepository.save(drug);
        return new DrugResponseDto(
                dg.getDrugId(),
                dg.getDrugName(),
                dg.getDrugDescription(),
                dg.getPeremption(),
                dg.getPrice(),
                dg.getCategory().getCategoryType(),
                dg.getCreation_date(),
                dg.getUpdate_date()
        );
    }

    public DrugResponseDto getById(long id){
        Drug dg = drugRepository.findById(id).get();
        if (dg == null) throw new RuntimeException("impossible : drug not found");

        return new DrugResponseDto(
                dg.getDrugId(),
                dg.getDrugName(),
                dg.getDrugDescription(),
                dg.getPeremption(),
                dg.getPrice(),
                dg.getCategory().getCategoryType(),
                dg.getCreation_date(),
                dg.getUpdate_date()
        );
    }
}
