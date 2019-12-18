package com.application.BeverageVendingMachine.Repository;

import com.application.BeverageVendingMachine.Entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}
