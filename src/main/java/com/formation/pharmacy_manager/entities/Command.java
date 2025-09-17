package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commandId;

    @NotNull
    private String pseudo;

    @NotNull
    private Date creation_date;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "command",cascade = CascadeType.REMOVE)
    private List<CommandDrug> commandDrugList = new ArrayList<>();

    @OneToOne(mappedBy = "command")
    private Payment payment;
}
