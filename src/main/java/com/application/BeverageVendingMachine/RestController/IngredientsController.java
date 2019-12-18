package com.application.BeverageVendingMachine.RestController;

import com.application.BeverageVendingMachine.Entity.Ingredients;
import com.application.BeverageVendingMachine.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientsController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/")
    public ResponseEntity getAllIngredients() {
        try {
            List<Ingredients> ingredientsList = ingredientService.getAllIngredients();
            return correctResponse(ingredientsList, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PostMapping("/")
    public ResponseEntity addIngredient(@RequestBody Ingredients ingredients) {
        try {
            Ingredients ingredients1 = ingredientService.addIngredient(ingredients);
            return correctResponse(ingredients1, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping("/")
    public ResponseEntity updateIngredients(@RequestBody Ingredients ingredients) {
        try {
            Ingredients ingredients1 = ingredientService.updateIngredients(ingredients);
            return correctResponse(ingredients1, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping("/{param}")
    public ResponseEntity deleteIngredients(@PathVariable Long param) {
        try {
            boolean output = ingredientService.deleteIngredients(param);
            return correctResponse(output, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getIngredients(@PathVariable Long id) {
        try {
            Ingredients ingredients = ingredientService.getIngredients(id);
            return correctResponse(ingredients, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    public ResponseEntity correctResponse(Object value, Object error, int statusCode, String message, HttpStatus status) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("value", value);
        response.put("error", error);
        response.put("status", statusCode);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity errorResponse(Exception ex) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
