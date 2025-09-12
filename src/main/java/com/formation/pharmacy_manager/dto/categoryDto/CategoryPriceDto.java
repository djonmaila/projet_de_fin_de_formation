package com.formation.pharmacy_manager.dto.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryPriceDto {
    private String categoryType;
    private double price;
}
