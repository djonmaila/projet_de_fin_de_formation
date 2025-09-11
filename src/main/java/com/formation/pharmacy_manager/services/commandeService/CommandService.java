package com.formation.pharmacy_manager.services.commandeService;

import com.formation.pharmacy_manager.dto.commandeDto.CommandeRequestDto;
import com.formation.pharmacy_manager.dto.commandeDto.CommandeResponseDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
// import com.formation.pharmacy_manager.entities.Drug;

import java.util.List;

public interface CommandService {
    CommandeResponseDto createCommande(CommandeRequestDto dto);
    List<DrugResponseDto> getListDrugToCommand(String pseudo);
    CommandeResponseDto updateCommande(long id,CommandeRequestDto dto);
    boolean existById(long id);
    String deleteById(long id);
}
