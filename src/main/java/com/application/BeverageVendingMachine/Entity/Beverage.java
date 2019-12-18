package com.application.BeverageVendingMachine.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
