package com.application.BeverageVendingMachine.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    @ManyToOne
    @JsonBackReference
    private Beverage beverage;

    @ManyToOne
    private inventories inventories;

    private Integer quantityRequired;
}
