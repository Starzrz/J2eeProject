package com.tickets.demo.Respository;

import com.tickets.demo.model.Perform;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ${zrz} on 2018/3/20.
 */
public interface PerformRepository extends JpaRepository<Perform,Long>{
    Perform findById(long id);
    Perform findByName(String name);
}
