package com.example.AttributesREU.Repository;


import com.example.AttributesREU.Models.Category;
import com.example.AttributesREU.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category> findAll();
    public Category findById(int accId);
    List<Category>findByCategoryName(String categoryName);
}
