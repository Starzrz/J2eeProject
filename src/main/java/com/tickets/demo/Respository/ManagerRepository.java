package com.tickets.demo.Respository;

import com.tickets.demo.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by ${zrz} on 2018/3/23.
 */
public interface ManagerRepository extends JpaRepository<Manager,String> {
   Manager findByUserId(String userId);
}
