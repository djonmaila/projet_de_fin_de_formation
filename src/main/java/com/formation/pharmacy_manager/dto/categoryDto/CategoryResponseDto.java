package com.formation.pharmacy_manager.dto.categoryDto;

import com.formation.pharmacy_manager.entities.Category;

public record CategoryResponseDto(
         long categoryId,
         String categoryType,
         String categoryName
) {


    public CategoryResponseDto categoryToResponse(Category category){
        return new CategoryResponseDto(
          category.getCategoryId(),
          category.getCategoryType(),
          category.getCategoryName()
        );
    }

  
}
