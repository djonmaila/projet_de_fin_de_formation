package com.formation.pharmacy_manager.services.commandeService;

import com.formation.pharmacy_manager.dto.commandeDto.*;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.User;
import com.formation.pharmacy_manager.repository.CommandRepository;
import com.formation.pharmacy_manager.repository.PatientRepository;
import com.formation.pharmacy_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandServiceImpl implements CommandService{
    private CommandRepository commandRepository;
    private UserRepository userRepository;
    private PatientRepository patientRepository;
    @Override
    public CommandeResponseDto createCommande(CommandeRequestDto dto) {
        if (verifyIfCommandNotEmpty(dto.getUserName())) {
            Command command = new Command();
            command.setPseudo(String.format("cmd%06d", commandRepository.count()+1));
            User user = userRepository.findDistinctByUserName(dto.getUserName());
            command.setUser(user);
            command.setCreation_date(LocalDate.now());

            Command cmd = commandRepository.save(command);
            return new CommandeResponseDto(
                    cmd.getCommandId(),
                    cmd.getPseudo(),
                    cmd.getUser().getUserName(),
                    cmd.getCreation_date()
            );
        }

        throw new RuntimeException("command empty existing any");
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
    public List<CommandDate> totalCommandPerDateForUser(String userName) {
        return commandRepository.totalCommandPassPerDateForUser(userName);
    }

    @Override
    public List<TotalMoneyPerCommand> totalRevenuCommand() {
        return commandRepository.totalRevenuCommand();
    }

    @Override
    public List<TotalMoneyPerCommand> totalRevenuCommandForUser(String user) {
        return commandRepository.totalRevenuCommandForUser(user);
    }

    @Override
    public List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrug() {
        return commandRepository.totalQuantityDrugInCommandDrug();
    }

    @Override
    public List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrugForUser(String user) {
        return commandRepository.totalQuantityDrugInCommandDrugForUser(user);
    }

    @Override
    public long totalQteDrugHavingCommand(String pseudo) {
        return commandRepository.totalQteDrugHavingCommand(pseudo);
    }

    @Override
    public List<CommandeResponseDto> getListCommandInThisDay(String userName) {
        return commandRepository.getListCommandInThisDay(userName).stream().map(
                cmd->new CommandeResponseDto(
                        cmd.getCommandId(),
                        cmd.getPseudo(),
                        cmd.getUser().getUserName(),
                        cmd.getCreation_date()
                )
        ).toList();
    }

    public boolean existById(long id){
        return commandRepository.existsById(id);
    }

    public CommandeResponseDto updateCommande(long id,CommandeRequestDto dto) {
        Command command =commandRepository.findById(id).orElse(null);
        if (command == null) throw new RuntimeException("impossible to update this command");
        User user = userRepository.findDistinctByUserName(dto.getUserName());
        command.setUser(user);
        command.setCreation_date(LocalDate.now());

        Command cmd = commandRepository.save(command);
        return new CommandeResponseDto(
                cmd.getCommandId(),
                cmd.getPseudo(),
                cmd.getUser().getUserName(),
                cmd.getCreation_date()
        );
    }

    @Override
    public boolean verifyIfCommandNotEmpty(String userName) {
        List<Command> cmdListEmpty = userRepository.getCommandEmpty(userName);
        if (!cmdListEmpty.isEmpty()){
            return true;
        }
        return false;
    }
}
