# Beverage Vending Machine

## Functionalities needed

1. Display all beverages available
2. 4 Types of beverages
	1. Black Coffee
		1. Water -> 3 unit
		2. Coffee -> 1 unit
		3. Sugar -> 1 unit
	2. Coffee with Milk
		1. Water -> 1 unit
		2. Coffee -> 1 unit
		3. Milk -> 2 unit
		4. Sugar -> 1 unit
	3. Sugarfree Black Coffee
		1. Water -> 3 unit
		2. Coffee -> 1 unit
	4. Sugarfree Coffee with Milk
		1. Water -> 1 unit
		2. Coffee -> 1 unit
		3. Milk -> 2 unit
3. Notify staff when it ran out of ingredients 


## Database Design

> Note : _italics_ text are primary keys and __bold__ are foreign keys

1. Inventories
	- _itemId -> integer_
	- itemName -> varchar
	- quantity -> integer
2. Beverage
	- _beverageId -> integer_
	- beverageName -> varchar
	- available -> boolean
3. Ingredients
	- _beverageIngredientId -> integer_ (Optional)
	- __itemId__ -> Inventories.itemId
	- __beverageId__ -> Beverage.beverageId
	- quantityNeeded -> integer
<!-- 4. Orders
	- _orderId -> Integer_
	- __beverageId__ -> Beverage.beverageId
	- orderTime -> DateTime -->


## Apis should be developed

### Inventories

[] Inventories add_inventory(Inventories) :- it will add new inventory which is available in machine.
[] Inventories update_inventory(Inventories) :- it will update inventor information.
[] Inventories update_quantity_of_inventor(Integer) :- it will specifically update quantity of specified inventory.
[] Inventories delete_inventory(Inventories) :- delete inventory which is now no longer supported by vending machine.
[] List<Inventories> view_all_inventories() :- display details of all the inventories which machine supports.
[] List<Inventories> view_finished_inventories() :- display inventory that need to be refilled in exam
[] List<Inventories> view_single_inventory(Integer) :- display details of requested inventory
[] boolean check_quantity_is_empty(Integer) :- checks whether the given inventory is emptied or not.
[] boolean check_for_given_quantity(Integer inventoryID,Integer qtyToCheck) :- checks whether the inventory has requested quantity or not.

### Beverages

[] Beverage add_beverage(Beverage) :- add beverage to the system
[] Beverage update_beverage(Beverage) :- update beverage to the system
[] Beverage delete_beverage(Beverage) :- delete beverage from the system
[] List<Beverage> view_beverages() :- view all beverages
[] Beverage view_single_beverage(Integer) :- display single requested beverage
[] Boolean beverage_availability(Integer) :- checks whether beverage is available or not
[] List<Beverage> view_available_beverage() :- display all the available beverages

### Ingredients

[] Ingredients add_Ingredients(Ingredients) :- add ingredients to the system
[] Ingredients update_Ingredients(Ingredients) :- updates ingredients to the system
[] Ingredients delete_Ingredients(Ingredients) :- delete ingredients from the system
[] List<Ingredients> view_all_Ingredients() :- displays all ingredients required for all beverage in the system
[] Items view_Ingredients_of_Beverage(Beverage) :- display items required for a given beverage