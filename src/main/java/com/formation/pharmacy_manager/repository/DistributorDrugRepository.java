package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.DistributorDrug;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

public interface DistributorDrugRepository extends JpaRepository<DistributorDrug,Long> {
    @Query("select distinct dis from DistributorDrug dis where dis.drug.drugName = :drugName and dis.distributor.userName = :userName")
    DistributorDrug getByUserNameAndDrugName(@Param("drugName") String drugName, @Param("userName") String userName);
}
