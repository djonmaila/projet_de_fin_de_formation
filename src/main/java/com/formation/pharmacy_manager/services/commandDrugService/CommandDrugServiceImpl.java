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
        dis.setQte(dis.getQte()-dto.getQuantity());
        dis.setUpdate_date(new Date());
        String userName = dis.getDistributor().getUserName();
        distributorDrugRepository.save(dis);


        CommandDrug cmdDrug = new CommandDrug();
        cmdDrug.setDrug(drug);
        cmdDrug.setCommand(command);
        cmdDrug.setTime(LocalTime.now());
        cmdDrug.setDate(LocalDate.now());
        cmdDrug.setQuantity(dto.getQuantity());
        CommandDrug cmd = commandeDrugRepository.save(cmdDrug);

        return new CommandeDrugResponseDto(
                cmd.getCommandDrugId(),
                cmd.getCommand().getPseudo(),
                cmd.getDrug().getDrugName(),
                cmd.getQuantity(),
                cmd.getDate(),
                cmd.getTime()
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
                        cmd.getDate(),
                        cmd.getTime()
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
                cmd.getDate(),
                cmd.getTime()
        )).orElse(null);
    }
}
