package com.formation.pharmacy_manager.services.commandeService;

import com.formation.pharmacy_manager.dto.commandeDto.*;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.User;
import com.formation.pharmacy_manager.repository.CommandRepository;
import com.formation.pharmacy_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandServiceImpl implements CommandService{
    private CommandRepository commandRepository;
    private UserRepository userRepository;
    @Override
    public CommandeResponseDto createCommande(CommandeRequestDto dto) {
        Command command =new Command();
        command.setPseudo(dto.getPseudo());
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

    @Override
    public List<CommandeResponseDto> getListCommand() {
        return commandRepository.findAll().stream().map(
                cmd-> new CommandeResponseDto(
                        cmd.getCommandId(),
                        cmd.getPseudo(),
                        cmd.getUser().getUserName(),
                        cmd.getCreation_date()
                )).toList();
    }

    public String deleteById(long id){
        if (commandRepository.existsById(id)){
            commandRepository.deleteById(id);
            return "command was deleting successfully";
        }
        return "impossible to delete this command because the command wasn't found";
    }

    @Override
    public List<CommandDate> totalCommandPerDate() {
        return commandRepository.totalCommandPassPerDate();
    }

    @Override
    public List<TotalMoneyPerCommand> totalRevenuCommand() {
        return commandRepository.totalRevenuCommand();
    }

    @Override
    public List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrug() {
        return commandRepository.totalQuantityDrugInCommandDrug();
    }

    @Override
    public long totalQteDrugHavingCommand(String pseudo) {
        return commandRepository.totalQteDrugHavingCommand(pseudo);
    }

    public boolean existById(long id){
        return commandRepository.existsById(id);
    }

    public CommandeResponseDto updateCommande(long id,CommandeRequestDto dto) {
        Command command =commandRepository.findById(id).orElse(null);
        if (command == null) throw new RuntimeException("impossible to update this command");
        command.setPseudo(dto.getPseudo());
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
}
