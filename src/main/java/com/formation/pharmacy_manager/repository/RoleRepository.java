package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Role;
import com.formation.pharmacy_manager.enumEntities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.type = :type")
    Role getByType(@Param("type") Type type);
}
