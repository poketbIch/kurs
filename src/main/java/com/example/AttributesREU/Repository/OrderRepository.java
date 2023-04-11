package com.example.AttributesREU.Repository;

import com.example.AttributesREU.Models.ClientOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ClientOrder,Long> {
    List<ClientOrder> findAll();
}
