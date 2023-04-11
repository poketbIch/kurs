package com.example.AttributesREU.Repository;

import com.example.AttributesREU.Models.Category;
import com.example.AttributesREU.Models.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {
    List<Supplier> findAll();
    public Supplier findById(int accId);
}
