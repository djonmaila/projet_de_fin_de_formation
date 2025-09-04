package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @NotBlank
    private String categoryType;

    @NotBlank
    private String categoryName;

    @NotBlank
    private LocalDate creation_date;

    @NotBlank
    private LocalDate update_date;

    @OneToMany(mappedBy = "category")
    private List<Drug> drugList = new ArrayList<>();
}
