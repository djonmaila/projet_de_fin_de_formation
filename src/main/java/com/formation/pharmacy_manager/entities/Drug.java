package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long drugId;

    @NotBlank
    private String drugName;

    @NotBlank
    private String drugDescription;
    private LocalDate peremption;
    private double price;
    private Date creation_date;
    private Date update_date;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @OneToMany(mappedBy = "drug")
    private List<DistributorDrug> distributorDrugList = new ArrayList<>();

    @OneToMany(mappedBy = "drug")
    private List<CommandDrug> commandDrugList = new ArrayList<>();

}
