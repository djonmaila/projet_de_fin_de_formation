package com.formation.pharmacy_manager.dto.categoryDto;
import com.formation.pharmacy_manager.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class CategoryRequestDto {
    private String categoryType;

    private String categoryName;

    public Category toCategoryEntityCreation(CategoryRequestDto dto){
        Category category = new Category();
        category.setCategoryType(dto.getCategoryType());
        category.setCategoryName(dto.getCategoryName());
        category.setCreation_date(LocalDate.now());
        category.setUpdate_date(LocalDate.now());
        return category;
    }
}
