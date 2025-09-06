package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug,Long> {
    Drug findDistinctByDrugName(String drugName);
}
