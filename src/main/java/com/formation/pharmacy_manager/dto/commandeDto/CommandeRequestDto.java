package com.formation.pharmacy_manager.dto.commandeDto;

import com.formation.pharmacy_manager.entities.Command;
import lombok.Getter;

@Getter
public class CommandeRequestDto {
    private String index;
    private String userName;

    public Command toCommandEntity(CommandeRequestDto dto){
        Command command = new Command();
        command.setPseudo(dto.index);
 
        return command;
    }
}
