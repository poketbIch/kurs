package com.example.AttributesREU.Repository;

import com.example.AttributesREU.Models.Item;
import com.example.AttributesREU.Models.Storage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
    List<Item> findAll();
    List<Item>findByCategoryCategoryName(String categoryName);
    List<Item> findByNameContaining(String name);
}
