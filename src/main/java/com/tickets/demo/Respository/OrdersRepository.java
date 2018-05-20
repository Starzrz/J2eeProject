package com.tickets.demo.Respository;

import com.tickets.demo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ${zrz} on 2018/3/20.
 */
public interface OrdersRepository extends JpaRepository<Orders,Long> {
  Orders findById(long id);
  List<Orders> findAllByUserEmail(String email);

  List<Orders> findAllByStatus(int status);

  List<Orders> findAllByUserEmailAndStatus(String email,int status);

}
