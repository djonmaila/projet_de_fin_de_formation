package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug,Long> {
    Drug findDistinctByDrugName(String drugName);

    List<Drug> findByCategory_CategoryType(String categoryCategoryType);
    @Query("select d from Drug d where d.drugName like :key%")
    List<Drug> searchByKeyWorld(@Param("key") String key);
}
