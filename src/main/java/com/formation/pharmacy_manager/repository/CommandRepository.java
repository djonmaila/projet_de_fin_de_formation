package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.dto.commandeDto.CommandDate;
import com.formation.pharmacy_manager.dto.commandeDto.TotalMoneyPerCommand;
import com.formation.pharmacy_manager.dto.commandeDto.TotalQuantityForDrugCommand;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandRepository extends JpaRepository<Command,Long> {
    Command findDistinctByPseudo(String pseudo);

    @Query("select cd.drug from Command c join c.commandDrugList cd where c.pseudo = :pseudo")
    List<Drug> getListDrugToCommand(@Param("pseudo") String pseudo);

    @Query("select new com.formation.pharmacy_manager.dto.commandeDto.CommandDate(d.date,count(c)) from Command c join c.commandDrugList d group by d.date")
    List<CommandDate> totalCommandPassPerDate();

    @Query("select new com.formation.pharmacy_manager.dto.commandeDto.CommandDate(d.date,count(c)) from Command c join c.commandDrugList d where c.user.userName = :userName group by d.date")
    List<CommandDate> totalCommandPassPerDateForUser(@Param("userName") String userName);

    @Query("select new com.formation.pharmacy_manager.dto.commandeDto.TotalMoneyPerCommand(c.pseudo , sum(cd.drug.price*cd.quantity)) from Command c join c.commandDrugList cd group by c.pseudo")
    List<TotalMoneyPerCommand> totalRevenuCommand();

    @Query("select new com.formation.pharmacy_manager.dto.commandeDto.TotalMoneyPerCommand(c.pseudo , sum(cd.drug.price*cd.quantity)) from Command c join c.commandDrugList cd where c.user.userName = :userName group by c.pseudo")
    List<TotalMoneyPerCommand> totalRevenuCommandForUser(@Param("userName")String userName);

    @Query("select new com.formation.pharmacy_manager.dto.commandeDto.TotalQuantityForDrugCommand(c.pseudo , sum(cd.quantity)) from Command c join c.commandDrugList cd group by c.pseudo")
    List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrug();

    @Query("select new com.formation.pharmacy_manager.dto.commandeDto.TotalQuantityForDrugCommand(c.pseudo , sum(cd.quantity)) from Command c join c.commandDrugList cd where c.user.userName = :userName group by c.pseudo")
    List<TotalQuantityForDrugCommand> totalQuantityDrugInCommandDrugForUser(@Param("userName") String userName);

    @Query("select sum(cd.quantity) from Command c join c.commandDrugList cd where c.pseudo = :pseudo")
    long totalQteDrugHavingCommand(@Param("pseudo") String pseudo);

    @Query("select sum(cd.quantity) from Command c join c.commandDrugList cd where c.pseudo = :pseudo and c.user.userName = :userName")
    long totalQteDrugHavingCommandForUser(@Param("pseudo") String pseudo,@Param("userName") String userName);

    @Query("select c from Command c join c.user u where u.userName = :userName and c.creation_date = current_date ")
    List<Command> getListCommandInThisDay(@Param("userName") String userName);

}
