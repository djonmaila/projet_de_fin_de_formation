package com.formation.pharmacy_manager.services.commandeService;

import com.formation.pharmacy_manager.dto.commandeDto.CommandeRequestDto;
import com.formation.pharmacy_manager.dto.commandeDto.CommandeResponseDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.entities.Command;
// import com.formation.pharmacy_manager.entities.Drug;
import com.formation.pharmacy_manager.entities.User;
import com.formation.pharmacy_manager.repository.CommandRepository;
import com.formation.pharmacy_manager.repository.UserRepository;
// import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
// @AllArgsConstructor
public class CommandServiceImpl implements CommandService{
    private CommandRepository commandRepository;
    private UserRepository userRepository;
    @Override
    public CommandeResponseDto createCommande(CommandeRequestDto dto) {
        Command command = dto.toCommandEntity(dto);
        User user = userRepository.findDistinctByUserName(dto.getUserName());
        command.setUser(user);
        command.setCreation_date(new Date());

        Command cmd = commandRepository.save(command);
        return new CommandeResponseDto(
                cmd.getCommandId(),
                cmd.getPseudo(),
                cmd.getUser().getUserName(),
                cmd.getCreation_date()
        );
    }

    @Override
    public List<DrugResponseDto> getListDrugToCommand(String pseudo) {
        return commandRepository.getListDrugToCommand(pseudo).stream().map(
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
}
