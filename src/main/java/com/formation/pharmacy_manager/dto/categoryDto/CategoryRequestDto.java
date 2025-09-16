package com.formation.pharmacy_manager.dto.categoryDto;
import com.formation.pharmacy_manager.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CategoryRequestDto {
    private String categoryType;

    private String categoryName;
}

