package com.formation.pharmacy_manager.services.commandeService;

import com.formation.pharmacy_manager.dto.commandeDto.*;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;

import java.util.List;

public interface CommandService {
    CommandeResponseDto createCommande(CommandeRequestDto dto);
    List<DrugResponseDto> getListDrugToCommand(String pseudo);
    List<CommandeResponseDto> getListCommand();
    CommandeResponseDto updateCommande(long id,CommandeRequestDto dto);
    boolean existById(long id);
    String deleteById(long id);
    List<CommandDate> totalCommandPerDate();
    List<TotalMoneyPerCommand> totalRevenuCommand();
    List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrug();
    long totalQteDrugHavingCommand(String pseudo);
}
