package com.formation.pharmacy_manager.dto.commandeDto;

import com.formation.pharmacy_manager.entities.Command;
import lombok.Getter;

@Getter
public class CommandeRequestDto {
    private String pseudo;
    private String userName;

    public Command toCommandEntity(CommandeRequestDto dto){
        Command command = new Command();
        command.setPseudo(dto.pseudo);

        return command;
    }
}
