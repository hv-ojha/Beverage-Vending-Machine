package com.application.BeverageVendingMachine.Service;

import com.application.BeverageVendingMachine.Entity.Beverage;
import com.application.BeverageVendingMachine.Entity.Ingredients;
import com.application.BeverageVendingMachine.Repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;

    public Beverage addBeverage(Beverage beverage) throws Exception {
        if(beverage == null)
            throw new Exception("No Beverage Sent");
        if(beverage.getName() == null)
            throw new Exception("Some value(s) are missing, Please check the manual properly");
        return beverageRepository.save(beverage);
    }

    public Beverage updateBeverage(Beverage beverage) throws Exception {
        Beverage beverage1 = getBeverage(beverage.getBeverageId());
        if(beverage1 == null)
            throw new Exception("No such Beverage exist");
        if(beverage.getName() == null)
            beverage.setName(beverage1.getName());
        if(beverage.getIngredients() == null)
            beverage.setIngredients(beverage1.getIngredients());
        return beverageRepository.save(beverage);
    }

    public Beverage getBeverage(Long id) throws Exception {
        Optional<Beverage> beverage = beverageRepository.findById(id);
        if(!beverage.isPresent())
            throw new Exception(("No Such beverage exist"));
        return beverage.get();
    }

    public List<Beverage> getBeverages() throws Exception {
        List<Beverage> beverageList = beverageRepository.findAll();
        if(beverageList.isEmpty())
            throw new Exception("No Beverage is present");
        return beverageList;
    }

    public boolean deleteBeverage(Long id) throws Exception {
        Beverage beverage = getBeverage(id);
        beverageRepository.delete(beverage);
        return true;
    }

    public boolean checkAvailability(Long id) throws Exception {
        Beverage beverage = getBeverage(id);
        return beverage.isAvailable();
    }

    public List<Beverage> checkAvailability() throws Exception {
        List<Beverage> beverageList = getBeverages();
        beverageList.removeIf(beverage -> (!beverage.isAvailable()));
        if(beverageList.isEmpty())
            throw new Exception("No Beverages are available");
        return beverageList;
    }

    public Beverage toggleAvailability(Beverage beverage) throws Exception {
        List<Ingredients> ingredientsList = beverage.getIngredients();
        for(Ingredients i : ingredientsList) {
            if(i.getInventories().getQuantity() < i.getQuantityRequired()) {
                beverage.setAvailable(false);
                break;
            }
        }
        return beverageRepository.save(beverage);
    }

    public void updateList() throws Exception {
        List<Beverage> beverageList = checkAvailability();
        for(Beverage b : beverageList) {
            toggleAvailability(b);
        }
    }
}
