package com.application.BeverageVendingMachine.Repository;

import com.application.BeverageVendingMachine.Entity.inventories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoriesRepository extends JpaRepository<inventories, Long> {

}
