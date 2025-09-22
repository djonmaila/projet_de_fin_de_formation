package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    @Query("select r from Request r join r.user u where u.userName = :user")
    List<Request> getListRequestForUser(@Param("user") String user);


    @Query("select r from Request r join r.user u where u.userName = :user and r.date = current_date ")
    List<Request> getListRequestForUserInThisDate(@Param("user") String user);
}
