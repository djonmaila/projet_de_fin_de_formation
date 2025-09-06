package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findDistinctByCategoryType(String categoryType);
}
