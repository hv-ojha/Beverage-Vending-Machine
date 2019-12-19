package com.application.BeverageVendingMachine.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long beverageId;

    private String name;

    private boolean available;

    @OneToMany(mappedBy = "beverage")
    @JsonManagedReference
    private List<Ingredients> ingredients;
}
