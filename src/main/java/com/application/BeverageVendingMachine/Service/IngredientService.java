package com.application.BeverageVendingMachine.Service;

import com.application.BeverageVendingMachine.Entity.Ingredients;
import com.application.BeverageVendingMachine.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredients addIngredient(Ingredients ingredients) throws Exception {
        if(ingredients == null)
            throw new Exception("No value provided");
        if(ingredients.getBeverage() == null || ingredients.getInventories() == null || ingredients.getQuantityRequired() == null)
            throw new Exception("Required Parameters are not provided, please check the manual");
        return ingredientRepository.save(ingredients);
    }

    public Ingredients updateIngredients(Ingredients ingredients) throws Exception {
        if(ingredients == null)
            throw new Exception("NO value provided");
        Ingredients ingredients1 = getIngredients(ingredients.getIngredientId());
        if(ingredients.getQuantityRequired() == null)
            ingredients.setQuantityRequired(ingredients1.getQuantityRequired());
        if(ingredients.getInventories() == null)
            ingredients.setInventories(ingredients1.getInventories());
        if(ingredients.getBeverage() == null)
            ingredients.setBeverage(ingredients1.getBeverage());
        return ingredientRepository.save(ingredients);
    }

    public Ingredients getIngredients(Long id) throws Exception {
        Optional<Ingredients> ingredients = ingredientRepository.findById(id);
        if(!ingredients.isPresent())
            throw new Exception("No such ingredient exist");
        return ingredients.get();
    }

    public List<Ingredients> getAllIngredients() throws Exception {
        List<Ingredients> ingredientsList = ingredientRepository.findAll();
        if(ingredientsList.isEmpty())
            throw new Exception("No ingredients inserted");
        return ingredientsList;
    }

    public boolean deleteIngredients(Long id) throws Exception {
        Ingredients ingredients = getIngredients(id);
        try {
            ingredientRepository.delete(ingredients);
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }

}
