package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
   User findDistinctByUserName(String userName);

    User findDistinctByEmail(String email);

    @Query("select c from User u join u.commandList c  where u.userName = :userName and c.commandDrugList is not empty")
    List<Command> getCommandNotEmpty(@Param("userName") String userName);

    @Query("select c from User u join u.commandList c where u.userName = :userName and c.commandDrugList is empty")
    List<Command> getCommandEmpty(@Param("userName") String userName);
}
