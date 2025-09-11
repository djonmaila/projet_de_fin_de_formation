package com.formation.pharmacy_manager.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import java.util.ArrayList;
import java.util.List;
 
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Distributor extends User {
    @NotBlank
    private String distributorReference;

    @OneToMany(mappedBy = "distributor", cascade = CascadeType.REMOVE)
    private List<DistributorDrug> distributorDrugList = new ArrayList<>();
}
