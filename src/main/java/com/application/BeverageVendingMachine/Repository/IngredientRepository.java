package com.application.BeverageVendingMachine.Repository;

import com.application.BeverageVendingMachine.Entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredients,Long> {

}
