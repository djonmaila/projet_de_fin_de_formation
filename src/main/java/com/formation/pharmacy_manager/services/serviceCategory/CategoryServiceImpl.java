package com.formation.pharmacy_manager.services.serviceCategory;

import com.formation.pharmacy_manager.dto.categoryDto.CategoryPriceDto;
import com.formation.pharmacy_manager.dto.categoryDto.CategoryRequestDto;
import com.formation.pharmacy_manager.dto.categoryDto.CategoryResponseDto;
import com.formation.pharmacy_manager.entities.Category;
import com.formation.pharmacy_manager.entities.Drug;
import com.formation.pharmacy_manager.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        Category category = new Category();
        category.setCategoryType(dto.getCategoryType());
        category.setCategoryName(dto.getCategoryName());
        category.setCreation_date(LocalDate.now());
        category.setUpdate_date(LocalDate.now());
        Category cat = categoryRepository.save(category);
        return new CategoryResponseDto(
                cat.getCategoryId(),
                cat.getCategoryType(),
                cat.getCategoryName()
        );
    }

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(
                category -> new CategoryResponseDto(
                        category.getCategoryId(),
                        category.getCategoryType(),
                        category.getCategoryName()
                )).toList();
    }

    @Override
    public CategoryResponseDto getById(long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new RuntimeException("category non trouvé");
        }
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryType(),
                category.getCategoryName()
        );
    }

    @Override
    public String deleteById(long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new RuntimeException("suppression impossible : category non trouvé");
        }
        List<Drug> drugList = category.getDrugList();

        for (Drug g : drugList){
            g.setCategory(null);
        }
        categoryRepository.deleteById(id);
        return "supprimé avec succès";
    }

    @Override
    public CategoryResponseDto updateCategory(long id,CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new RuntimeException("mise à jour impossible : category non trouvé");
        }

        category.setCategoryName(dto.getCategoryName());
        category.setCategoryType(dto.getCategoryType());
        category.setUpdate_date(LocalDate.now());

        Category cat =categoryRepository.save(category);
        return new CategoryResponseDto(
                cat.getCategoryId(),
                cat.getCategoryType(),
                cat.getCategoryName()
        );
    }

    @Override
    public List<CategoryPriceDto> getCategoryBySumPrice() {
        return categoryRepository.getCategoryBySumPrice();
    }
}
