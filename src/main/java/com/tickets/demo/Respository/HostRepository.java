package com.tickets.demo.Respository;

import com.tickets.demo.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ${zrz} on 2018/3/23.
 */
public interface HostRepository extends JpaRepository<Host,Long> {
    Host findById(long id);
    List<Host> findAllByIsRatify(int is);
}
