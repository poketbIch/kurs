package com.example.AttributesREU.Repository;

import com.example.AttributesREU.Models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByPhonenumber(String username);
    User findUserByUsername(String login);
    List<User> findAll();
    public User findById(int accId);
}
