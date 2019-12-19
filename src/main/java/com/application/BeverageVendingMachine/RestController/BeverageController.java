package com.application.BeverageVendingMachine.RestController;

import com.application.BeverageVendingMachine.Entity.Beverage;
import com.application.BeverageVendingMachine.Entity.Ingredients;
import com.application.BeverageVendingMachine.Service.BeverageService;
import com.application.BeverageVendingMachine.Service.InventoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class BeverageController {

    @Autowired
    private BeverageService beverageService;

    @Autowired
    private InventoriesService inventoriesService;

    @GetMapping("/")
    public ResponseEntity getAllBeverages() {
        try {
            List<Beverage> list = beverageService.getBeverages();
            return correctResponse(list,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PostMapping("/")
    public ResponseEntity addBeverage(@RequestBody Beverage beverage) {
        try {
            Beverage beverage1 = beverageService.addBeverage(beverage);
            return correctResponse(beverage1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping("/")
    public ResponseEntity updateBeverage(@RequestBody Beverage beverage) {
        try {
            Beverage beverage1 = beverageService.updateBeverage(beverage);
            return correctResponse(beverage1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping("/{param}")
    public ResponseEntity deleteBeverage(@PathVariable Long param) {
        try {
            beverageService.deleteBeverage(param);
            return correctResponse("Beverage deleted successfully",HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getBeverage(@PathVariable Long id) {
        try {
            Beverage beverage = beverageService.getBeverage(id);
            return correctResponse(beverage,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/available/{id}")
    public ResponseEntity checkAvailability(@PathVariable Long id) {
        try {
            boolean output = beverageService.checkAvailability(id);
            return correctResponse(output,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/available")
    public ResponseEntity checkAvailability() {
        try {
            List<Beverage> beverageList = beverageService.checkAvailability();
            return correctResponse(beverageList,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity orderBeverage(@PathVariable Long id) {
        try {
            if(!beverageService.checkAvailability(id)) {
                List<Beverage> beverageList = beverageService.checkAvailability();
                return correctResponse(beverageList,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"Requested Beverage is not available. Here are the beverages which are available",HttpStatus.BAD_REQUEST);
            }
            else {
                Beverage beverage = beverageService.getBeverage(id);
                List<Ingredients> ingredientsList = beverage.getIngredients();
                for(Ingredients i : ingredientsList) {
                    inventoriesService.reduceInventories(i.getInventories(),i.getQuantityRequired());
                }
                beverage = beverageService.updateBeverage(beverageService.toggleAvailability(beverage));
                return correctResponse(beverage,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            }
        }
        catch(Exception ex) {
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
