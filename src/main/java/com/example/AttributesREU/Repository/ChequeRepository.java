package com.example.AttributesREU.Repository;

import com.example.AttributesREU.Models.Cheque;
import com.example.AttributesREU.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChequeRepository extends CrudRepository<Cheque,Long> {
    List<Cheque> findAll();
    List<Cheque> findAllByUser(User user);
}
