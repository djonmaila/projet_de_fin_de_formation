package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor 
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DistributorDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long distributorDrugId;
    @PositiveOrZero
    private int qte;
    private Date creation_date;
    private Date update_date;
    @ManyToOne
    @JoinColumn(name = "id_drug")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "id_distributor")
    private Distributor distributor;
}