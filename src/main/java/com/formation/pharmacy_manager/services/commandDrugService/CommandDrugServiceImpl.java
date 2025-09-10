package com.formation.pharmacy_manager.services.commandDrugService;

import com.formation.pharmacy_manager.dto.commandeDrugDto.CommandeDrugRequestDto;
import com.formation.pharmacy_manager.dto.commandeDrugDto.CommandeDrugResponseDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.CommandDrug;
import com.formation.pharmacy_manager.entities.DistributorDrug;
import com.formation.pharmacy_manager.entities.Drug;
import com.formation.pharmacy_manager.repository.CommandRepository;
import com.formation.pharmacy_manager.repository.CommandeDrugRepository;
import com.formation.pharmacy_manager.repository.DistributorDrugRepository;
import com.formation.pharmacy_manager.repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandDrugServiceImpl implements CommandDrugService{
    private CommandeDrugRepository commandeDrugRepository;
    private DrugRepository drugRepository;
    private CommandRepository commandRepository;
    private DistributorDrugRepository distributorDrugRepository;
    @Override
    public CommandeDrugResponseDto create(CommandeDrugRequestDto dto) {
        Drug drug = drugRepository.findDistinctByDrugName(dto.getDrugName());
        Command command = commandRepository.findDistinctByPseudo(dto.getPseudo());


        DistributorDrug dis =drug.getDistributorDrugList().stream().filter(fil->fil.getQte()> dto.getQuantity()).findAny().get();
        if (dis == null) throw new RuntimeException("stock insuffisant");
        dis.setQte(dis.getQte()-dto.getQuantity());
        dis.setUpdate_date(new Date());
        // String userName = dis.getDistributor().getUserName();
        distributorDrugRepository.save(dis);


        CommandDrug cmdDrug = new CommandDrug();
        cmdDrug.setDrug(drug);
        cmdDrug.setCommand(command);
        cmdDrug.setTime(LocalTime.now());
        cmdDrug.setDate(LocalDate.now());
        cmdDrug.setQuantity(dto.getQuantity());
        cmdDrug.setUserDis(userName);
        CommandDrug cmd = commandeDrugRepository.save(cmdDrug);

        return new CommandeDrugResponseDto(
                cmd.getCommandDrugId(),
                cmd.getCommand().getPseudo(),
                cmd.getDrug().getDrugName(),
                cmd.getQuantity(),
                cmd.getDrug().getPrice(),
                cmd.getDate(),
                cmd.getTime(),
                cmd.getUserDis()
        );
    }

    @Override
    public List<CommandeDrugResponseDto> getAllCommandDrug() {
        return commandeDrugRepository.findAll().stream().map(
                cmd->new CommandeDrugResponseDto(
                        cmd.getCommandDrugId(),
                        cmd.getCommand().getPseudo(),
                        cmd.getDrug().getDrugName(),
                        cmd.getQuantity(),
                        cmd.getDrug().getPrice(),
                        cmd.getDate(),
                        cmd.getTime(),
                        cmd.getUserDis()
                )).toList();
    }

    @Override
    public CommandeDrugResponseDto getById(long id) {
        return commandeDrugRepository.findById(id).map(
                cmd -> new CommandeDrugResponseDto(
                cmd.getCommandDrugId(),
                cmd.getCommand().getPseudo(),
                cmd.getDrug().getDrugName(),
                cmd.getQuantity(),
                cmd.getDrug().getPrice(),
                cmd.getDate(),
                cmd.getTime(),
                        cmd.getUserDis()
        )).orElse(null);
    }

    @Override
    public String deleteById(long id) {
            CommandDrug cmd = commandeDrugRepository.findById(id).orElse(null);
            if (cmd == null) throw new RuntimeException("command line doesn't exist");
            DistributorDrug dis = distributorDrugRepository.getByUserNameAndDrugName(cmd.getDrug().getDrugName(),cmd.getUserDis());
            dis.setQte(dis.getQte()+ cmd.getQuantity());
            dis.setUpdate_date(new Date());
            distributorDrugRepository.save(dis);

            commandeDrugRepository.deleteById(id);

            return "command line was successfully deleting";
    }

    @Override
    public boolean existById(long id) {
        return distributorDrugRepository.existsById(id);
    }

    @Override
    public CommandeDrugResponseDto update(long id, CommandeDrugRequestDto dto) {
        CommandDrug cmd = commandeDrugRepository.findById(id).orElse(null);

        if (cmd == null) throw new RuntimeException("Command line cannot update because he doesn't exist");
        cmd.setQuantity(dto.getQuantity());
        cmd.setTime(LocalTime.now());
        CommandDrug cmde = commandeDrugRepository.save(cmd);

        return new CommandeDrugResponseDto(
                cmde.getCommandDrugId(),
                cmde.getCommand().getPseudo(),
                cmde.getDrug().getDrugName(),
                cmde.getQuantity(),
                cmde.getDrug().getPrice(),
                cmde.getDate(),
                cmde.getTime(),
                cmde.getUserDis()
        );
    }
}
