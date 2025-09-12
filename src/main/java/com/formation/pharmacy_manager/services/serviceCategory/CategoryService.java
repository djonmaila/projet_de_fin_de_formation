package com.formation.pharmacy_manager.services.serviceCategory;

import com.formation.pharmacy_manager.dto.categoryDto.CategoryPriceDto;
import com.formation.pharmacy_manager.dto.categoryDto.CategoryRequestDto;
import com.formation.pharmacy_manager.dto.categoryDto.CategoryResponseDto;
import com.formation.pharmacy_manager.entities.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto dto);
    List<CategoryResponseDto> getAllCategory();
    CategoryResponseDto getById(long id);
    String deleteById(long id);
    CategoryResponseDto updateCategory(long id,CategoryRequestDto dto);
    List<CategoryPriceDto> getCategoryBySumPrice();
}
