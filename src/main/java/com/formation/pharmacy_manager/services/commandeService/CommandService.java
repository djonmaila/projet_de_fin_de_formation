package com.formation.pharmacy_manager.services.commandeService;

import com.formation.pharmacy_manager.dto.commandeDto.*;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.entities.Command;

import java.util.List;

public interface CommandService {
    CommandeResponseDto createCommande(CommandeRequestDto dto);
    List<DrugResponseDto> getListDrugToCommand(String pseudo);
    List<CommandeResponseDto> getListCommand();
    CommandeResponseDto updateCommande(long id,CommandeRequestDto dto);
    boolean existById(long id);
    String deleteById(long id);
    List<CommandDate> totalCommandPerDate();
    List<CommandDate> totalCommandPerDateForUser(String userName);
    List<TotalMoneyPerCommand> totalRevenuCommand();
    List<TotalMoneyPerCommand> totalRevenuCommandForUser(String user);
    List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrug();
    List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrugForUser(String user);
    long totalQteDrugHavingCommand(String pseudo);
    List<CommandeResponseDto> getListCommandInThisDay(String userName);
    boolean verifyIfCommandNotEmpty(String userName);
}
