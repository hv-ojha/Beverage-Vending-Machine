package com.application.BeverageVendingMachine.RestController;


import com.application.BeverageVendingMachine.Entity.inventories;
import com.application.BeverageVendingMachine.Service.InventoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoriesRestController {
    @Autowired
    private InventoriesService inventoriesService;

    @GetMapping("/")
    public ResponseEntity getAllInventories() {
        HashMap<Object, Object> response = new HashMap<>();
        try {
            List<inventories> list = inventoriesService.getInventories();
            return correctResponse(list,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity addInventories(@RequestBody inventories inventories) {
        try {
            inventories added = inventoriesService.addInventory(inventories);
            return correctResponse(added,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping("/")
    public ResponseEntity updateInventories(@RequestBody inventories inventories) {
        try {
            inventories added = inventoriesService.updateInventories(inventories);
            return correctResponse(added,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping(path = "/{parameter}")
    public ResponseEntity deleteInventories(@PathVariable Long parameter) {
        try {
            if (inventoriesService.deleteInventories(parameter)) {
                return correctResponse("Inventory deleted successfully",HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            } else {
                return correctResponse("Inventory not deleted",HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"No such Inventory exist",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/{parameter}")
    public ResponseEntity getInventory(@PathVariable Long parameter) {
        try {
            inventories inv = inventoriesService.getInventory(parameter);
            return correctResponse(inv,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex){
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/increaseQuantity/{inventory}/{quantity}")
    public ResponseEntity updateQuantity(@PathVariable Long inventory, @PathVariable Integer quantity) {
        try {
            inventories inventories = inventoriesService.updateQuantity(inventory, quantity);
            if (inventories == null) {
                return correctResponse("Null",HttpStatus.NO_CONTENT,HttpStatus.NO_CONTENT.value(),"No Such Inventory Exist",HttpStatus.NO_CONTENT);
            } else {
                return correctResponse(inventories,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            }
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/checkEmpty/{inventory}")
    public ResponseEntity checkEmpty(@PathVariable Long inventory) {
        try {
            HashMap<Object, Object> obj = new HashMap<>();
            boolean output = inventoriesService.checkEmpty(inventory);
            obj.put("value", output);
            String message;
            if(!output) {
                obj.put("inventory", inventoriesService.getInventory(inventory));
                message = "Inventory is not empty";
            }
            else
                message = "Inventory is empty";
            return correctResponse(obj,false,HttpStatus.OK.value(),message,HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/checkQuantity/{inventory}/{quantity}")
    public ResponseEntity checkQuantity(@PathVariable Long inventory, @PathVariable Integer quantity) {
        try {
            HashMap<Object, Object> obj = new HashMap<>();
            boolean output = inventoriesService.checkQuantity(inventory, quantity);
            obj.put("value",output);
            String message;
            if (output) {
                obj.put("inventory", inventoriesService.getInventory(inventory));
                message = "Inventory is sufficient";
            } else {
                message = "Inventory is not sufficient";
            }
            return correctResponse(obj,false,HttpStatus.OK.value(),message,HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/emptyInventories")
    public ResponseEntity getEmptyInventories() {
        try {
            List<inventories> list = inventoriesService.getAllEmptiedInventories();
            return correctResponse(list,false,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    public ResponseEntity correctResponse(Object value, Object error, int statusCode, String message,HttpStatus status) {
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
