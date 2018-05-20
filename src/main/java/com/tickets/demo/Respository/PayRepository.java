package com.tickets.demo.Respository;

import com.tickets.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ${zrz} on 2018/3/21.
 */
public interface PayRepository extends JpaRepository<Payment,String>{
    public Payment findByUserId(String userId);
}
