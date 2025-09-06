package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.categoryDto.CategoryRequestDto;
import com.formation.pharmacy_manager.dto.categoryDto.CategoryResponseDto;
import com.formation.pharmacy_manager.services.serviceCategory.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryRequestDto dto){
        return ResponseEntity.status(200).body(categoryService.createCategory(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable long id, @RequestBody CategoryRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id,dto));
    }
}
