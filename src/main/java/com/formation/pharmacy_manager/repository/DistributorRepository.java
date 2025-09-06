package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Distributor;
import com.formation.pharmacy_manager.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistributorRepository extends JpaRepository<Distributor,Long> {
    @Query("select distinct p from Distributor dis join dis.distributorDrugList drug join drug.drug p where dis.userName = :name")
    public List<Drug> getDrugFromDistributor(@Param("name") String name);

    Distributor findDistinctByEmail(String email);

    Distributor findDistinctByUserName(String userName);
}
