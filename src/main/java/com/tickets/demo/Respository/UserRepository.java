package com.tickets.demo.Respository;

import com.tickets.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ${zrz} on 2018/3/5.
 */
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);

}
