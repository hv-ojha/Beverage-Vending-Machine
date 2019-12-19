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

## Technologies Used

* Java (Spring Boot Framework)
* PostgreSQL
* Github
* Postman
* IntelliJ IDEA

## How To Run

* Open PostgreSQL and create Database with name `vendingmachine`
* Import Queries from `Queries.sql` to your PostgreSQL
* Open/Import project in InteliiJ or Eclipse
* Click on Run Application
* Open Postman For best interface for APIs.

## REST API

### 1. Inventories

#### 1.1 __Get All Inventories__

* URL - `http://localhost:8080/inventories/`
* Method - GET

* Purpose - Retrieve List of all inventories supported by vending machine.
* Parameter - NONE
* Body Params - NONE
* Result - List of inventories object

#### 1.2 __Add Inventory__

* URL - `http://localhost:8080/inventories/`
* Method - POST

* Purpose - Add new inventory to the system
* Parameters - NONE
* Body Params :- All parameters are permissible to send with request

| Parameters | Value | Datatype | Required |
|------------|-------|----------| -------- |
| `name` | should contain  name of new inventory | String | ___YES___ |
| `quantity` | should contain quantity inserted in the machine | Integer | ___YES___ |

* Result - Inventories object

#### 1.3 __Update Inventory__

* URL - `http://localhost:8080/inventories/`
* Method - PUT
* Purpose - Update existing inventory from the system

* Parameters - NONE
* Body Params :- All parameters are permissible to send with request

| Parameters | Value | Datatype | Required |
|------------|-------|----------| -------- |
| `inventoryId` | must be a valid id of existing inventory | Integer | ___YES___ |
| `name` | should contain  name of new inventory | String | ___NO___ |
| `quantity` | should contain quantity inserted in the machine | Integer | ___NO___ |

* Result - inventories object

#### 1.4 __Delete Inventory__

 * URL - `http://localhost:8080/inventories/{inventoryId}`
 * Method - DELETE
 
 * Purpose - Delete an existing inventory from the system
 * Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `inventoryId` | must be a valid id of existing inventory | Integer | ___YES___ |
 
 * Body Params :- NONE
 
 * Result - Boolean
 
 #### 1.5 __Get Single Inventory__
 
 * URL - `http://localhost:8080/inventories/{inventoryId}`
 * Method - GET
 
 * Purpose - Retrieve an inventory from the system requested by given ID.
 * Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `inventoryId` | must be a valid id of existing inventory | Integer | ___YES___ |
 
 * Body Params :- NONE
 
 * Result - inventories object
 
 #### 1.6 __Increase Inventory Quantity__
 
  * URL - `http://localhost:8080/inventories/increaseQuantity/{inventoryId}/{quantity}`
  * Method - GET
  * Purpose - Increase the quantity of an existing inventory from the system
  
  * Parameters - All parameters are permissible to send with request
  
  | Parameters | Value | Datatype | Required |
  |------------|-------|----------| -------- |
  | `inventoryId` | must be a valid id of existing inventory | Integer | ___YES___ |
  | `quantity` | must be a valid integer quantity | Integer | ___YES___ |
  
  * Body Params :- NONE
  * Result - inventories object
  
#### 1.7 __Check for Empty Inventory__
  
* URL - `http://localhost:8080/inventories/checkEmpty/{inventoryId}`
* Method - GET
* Purpose - Check whether the asked inventory is empty or not

* Parameters - All parameters are permissible to send with request

| Parameters | Value | Datatype | Required |
|------------|-------|----------| -------- |
| `inventoryId` | must be a valid id of existing inventory | Integer | ___YES___ |

* Body Params :- NONE
* Result - Boolean

#### 1.8 __Check Inventory with given quantity__

  * URL - `http://localhost:8080/inventories/checkQuantity/{inventoryId}/{quantity}`
  * Method - GET
  * Purpose - Check whether the asked inventory possess given quantity or not
  
  * Parameters - All parameters are permissible to send with request
  
  | Parameters | Value | Datatype | Required |
  |------------|-------|----------| -------- |
  | `inventoryId` | must be a valid id of existing inventory | Integer | ___YES___ |
  | `quantity` | must be a valid integer quantity | Integer | ___YES___ |
  
  * Body Params :- NONE
  * Result - Boolean

#### 1.9 __Empty Inventories__

  * URL - `http://localhost:8080/inventories/emptyInventories`
  * Method - GET
  * Purpose - Retrieves all the inventories which are empty
  * Parameters - NONE
  
  * Body Params :- NONE
  * Result - List of inventories object
  

### 2 __Beverage__

#### 2.1 __Add Beverage__

* URL - `http://localhost:8080/`
* Method - POST
* Purpose - Add new beverage to the system

* Parameters - NONE
* Body Params :- All parameters are permissible to send with request

| Parameters | Value | Datatype | Required |
|------------|-------|----------| -------- |
| `beverageId` | must be a valid id of existing inventory | Integer | ___NO___ |
| `name` | should contain  name of new inventory | String | __YES__ |
| `available` | boolean value for availability of beverage | Boolean | ___NO___ |

* Result - Beverage object

#### 2.2 __Get All Beverages__

* URL - `http://localhost:8080/`
* Method - GET
* Purpose - Retrieve all the beverages of the system

* Parameters - NONE
* Body Params :- NONE
* Result - List of Beverage object

#### 2.3 __Update Beverage__

* URL - `http://localhost:8080/`
* Method - PUT
* Purpose - Update existing beverage from the system

* Parameters - NONE
* Body Params :- All parameters are permissible to send with request

| Parameters | Value | Datatype | Required |
|------------|-------|----------| -------- |
| `beverageId` | must be a valid id of existing inventory | Integer | ___YES___ |
| `name` | should contain  name of new inventory | String | ___NO___ |
| `available` | boolean value for availability of beverage | Boolean | ___NO___ |

* Result - Beverage object

#### 2.4 __Get Single Beverage__

* URL - `http://localhost:8080/{beverageId}`
 * Method - GET
 
 * Purpose - Retrieve an beverage from the system requested by given ID.
 * Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `beverageId` | must be a valid id of existing beverage | Integer | ___YES___ |
 
 * Result - Beverage object
 * Body Params :- NONE

#### 2.5 __Delete Beverage__

 * URL - `http://localhost:8080/{beverageId}`
 * Method - DELETE
 
 * Purpose - Delete an existing beverage from the system
 * Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `beverageId` | must be a valid id of existing beverage | Integer | ___YES___ |
 
 * Body Params :- NONE
 * Result - Boolean

#### 2.6 __Check Availability of Single Beverage__

* URL - `http://localhost:8080/available/{beverageId}`

* Method - GET
* Purpose - Retrieve all beverages which are available in the vending machine
* Parameters - All parameters are permissible to send with request
  
  | Parameters | Value | Datatype | Required |
  |------------|-------|----------| -------- |
  | `beverageId` | must be a valid id of existing beverage | Integer | ___YES___ |
  
* Body Params - NONE
* Result - Boolean


#### 2.7 __Check all beverages__

* URL - `http://localhost:8080/available`

* Method - GET
* Purpose - Retrieve all beverages which are available in the vending machine
* Parameters - NONE
* Body Params - NONE
* Result - List of Beverage object

### 3. Ingredients

#### 3.1 __Get All Ingredients__

* URL - `http://localhost:8080/ingredient/`

* Method - GET
* Purpose - Retrieve all ingredients used by all beverages.
* Parameters - NONE
* Body Params - NONE
* Result - List of Ingredients object
> Alternative way is to call a single beverage api which will also contain information of ingredients used to create that beverage.

#### 3.2 __Add Ingredients__

* URL - `http://localhost:8080/ingredient/`

* Method - POST
* Purpose - Insert ingredient of particular beverage.
* Parameters - NONE
* Parameters - All parameters are permissible to send with request
  
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `beverage` | Beverage object with valid beverageId under the field `beverageId` inside beverage object | Integer | ___YES___ |
 | `inventories` | Beverage object with valid inventoryId under the field `inventoryId` inside inventories object | Integer | ___YES___ |
 | `quantityRequired` | Quantity required of that inventory to create that beverage | Integer | ___YES___ | 

* Result - Ingredients object

#### 3.3 __Update Ingredients__

* URL - `http://localhost:8080/ingredient/`

* Method - PUT
* Purpose - Update ingredient of particular beverage.
* Parameters - NONE
* Parameters - All parameters are permissible to send with request
  
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `beverage` | Beverage object with valid beverageId under the field `beverageId` inside beverage object | Integer | ___YES___ |
 | `inventories` | Beverage object with valid inventoryId under the field `inventoryId` inside inventories object | Integer | ___YES___ |
 | `quantityRequired` | Quantity required of that inventory to create that beverage | Integer | ___YES___ | 

* Result - Ingredients object

#### 3.4 __Get Single Ingredients__

* URL - `http://localhost:8080/ingredient/{ingredientId}`

* Method - GET
* Purpose - Retrieve information of single ingredient.
* Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `ingredientId` | must be a valid id of existing ingredients | Integer | ___YES___ |
 
 * Result - Ingredients object
 
* Parameters - NONE

#### 3.5 __Delete Ingredients__

* URL - `http://localhost:8080/ingredient/{ingredientId}`

* Method - DELETE
* Purpose - Delete an existing ingredient.
* Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `ingredientId` | must be a valid id of existing ingredients | Integer | ___YES___ |
 
* Parameters - NONE
* Result - Boolean

#### 3.6 __Order Beverage__

* URL - `http://localhost:8080/order/{beverageId}`

* Method - GET
* Purpose - Orders a beverage with valid beverageId
* Parameters - All parameters are permissible to send with request
 
 | Parameters | Value | Datatype | Required |
 |------------|-------|----------| -------- |
 | `beverageId` | must be a valid id of existing beverage | Integer | ___YES___ |
 
* Parameters - NONE

* Result - Ingredients object