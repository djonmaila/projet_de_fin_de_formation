package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandRepository extends JpaRepository<Command,Long> {
    Command findDistinctByPseudo(String pseudo);
    @Query("select d from Command c join c.commandDrugList cd join cd.drug d where c.pseudo = :pseudo")
    List<Drug> getListDrugToCommand(@Param("pseudo") String pseudo);
}
