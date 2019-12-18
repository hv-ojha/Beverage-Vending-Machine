package com.application.BeverageVendingMachine.Service;

import com.application.BeverageVendingMachine.Entity.Beverage;
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
}
