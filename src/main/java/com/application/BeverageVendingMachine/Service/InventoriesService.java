package com.application.BeverageVendingMachine.Service;

import com.application.BeverageVendingMachine.Entity.inventories;
import com.application.BeverageVendingMachine.Repository.InventoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoriesService {

    @Autowired
    private InventoriesRepository inventoriesRepository;

    public inventories addInventory(inventories inventories) throws Exception {
        if(inventories == null) {
            throw new Exception("No inventory received");
        }
        inventories inventories1 = inventoriesRepository.save(inventories);
        return inventories1;
    }

    public List<inventories> getInventories() throws Exception {
        List<inventories> listOfInventories = inventoriesRepository.findAll();
        if(listOfInventories == null) {
            throw new Exception("NO Inventories Found");
        }
        return listOfInventories;
    }

    public inventories updateInventories(inventories inventories) throws Exception {
        if(inventories == null) {
            throw new Exception("No inventory received");
        }
        inventories inventories1 = inventoriesRepository.findById(inventories.getInventoryId()).get();
        if(inventories1 == null)
            throw new Exception("No such inventory exist");

        if(inventories.getName() == null)
            inventories.setName(inventories1.getName());

        if(inventories.getQuantity() == null)
            inventories.setQuantity(inventories1.getQuantity());

        return inventoriesRepository.save(inventories);
    }

    public boolean deleteInventories(Long id) throws Exception {
        Optional<inventories> inventories = inventoriesRepository.findById(id);
        if(!inventories.isPresent()) {
            return false;
        }
        try {
            inventoriesRepository.delete(inventories.get());
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public inventories getInventory(Long id) throws Exception {
        Optional<inventories> inventories = inventoriesRepository.findById(id);
        if(!inventories.isPresent())
            throw new Exception("Inventory doesn't exist");
        return inventories.get();
    }

    public inventories updateQuantity(Long inventoryId, Integer quantity) throws Exception {
        Optional<inventories> inventories = inventoriesRepository.findById(inventoryId);
        if(!inventories.isPresent())
            throw new Exception("No such inventory exist");
        inventories inventories1 = inventories.get();
        inventories1.setQuantity(inventories1.getQuantity() + quantity);
        return inventoriesRepository.save(inventories1);
    }

    public boolean checkEmpty(Long inventoryId) throws Exception {
        try {
            inventories inventories = getInventory(inventoryId);
            return inventories.getQuantity() == 0;
        }
        catch (NullPointerException ex) {
            throw new Exception("No such inventory exist");
        }
    }

    public boolean checkQuantity(Long inventoryId, Integer quantity) throws Exception {
        try {
            inventories inventories = getInventory(inventoryId);
            return inventories.getQuantity() >= quantity;
        }
        catch (NullPointerException ex) {
            throw new Exception("No such inventory exist");
        }
    }

    public List<inventories> getAllEmptiedInventories() throws Exception {
        try {
            List<inventories> list = getInventories();
            list.removeIf(n -> (n.getQuantity() > 1));
            if(list.isEmpty())
                throw new Exception("Stock is available");
            return list;
        }
        catch (NullPointerException ex) {
            throw new Exception("Unexpected Error");
        }
    }
}
