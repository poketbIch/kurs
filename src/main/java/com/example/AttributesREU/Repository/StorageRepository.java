package com.example.AttributesREU.Repository;

import com.example.AttributesREU.Models.Storage;
import com.example.AttributesREU.Models.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StorageRepository extends CrudRepository<Storage,Long> {
    List<Storage> findAll();

}
