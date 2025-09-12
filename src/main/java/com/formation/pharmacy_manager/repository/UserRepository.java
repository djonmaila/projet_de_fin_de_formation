package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   User findDistinctByUserName(String userName);

    User findDistinctByEmail(String email);
}
