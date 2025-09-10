package com.formation.pharmacy_manager.services.commandDrugService;

import com.formation.pharmacy_manager.dto.commandeDrugDto.CommandeDrugRequestDto;
import com.formation.pharmacy_manager.dto.commandeDrugDto.CommandeDrugResponseDto;

import java.util.List;


public interface CommandDrugService {
    CommandeDrugResponseDto create(CommandeDrugRequestDto dto);
    List<CommandeDrugResponseDto> getAllCommandDrug();
    CommandeDrugResponseDto getById(long id);
    String deleteById(long id);
    boolean existById(long id);
    CommandeDrugResponseDto update(long id,CommandeDrugRequestDto dto);
}
