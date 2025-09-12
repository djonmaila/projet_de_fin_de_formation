package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.dto.categoryDto.CategoryPriceDto;
import com.formation.pharmacy_manager.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findDistinctByCategoryType(String categoryType);

    @Query("select new com.formation.pharmacy_manager.dto.categoryDto.CategoryPriceDto(c.categoryType,sum(d.price)) from Category c join c.drugList d group by (c.categoryType)")
    List<CategoryPriceDto> getCategoryBySumPrice();
}
