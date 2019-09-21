package com.glt.sdj.dao;

import com.glt.sdj.model.DO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
}
