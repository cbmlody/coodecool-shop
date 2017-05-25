**TEST DOCUMENTATIONS**

**DatabaseConnection:**

_REFACTOR:_

- Constructor method takes now String 'pathToDatabase' as parameter.
- Handling SQLException in openConnection method moved to App class.

_TESTS:_

- Test openConnection throws SQLException when incorrect 
'pathToDatabase' param was given.
- Test getConnection returns open connection after casted openConnection method.
- Test getConnection returns 'null' casted before openConnection method.
- Test closeConnection method is closing connection.
- Test migrateDB creates empty database with given tables name. 
Test is creating new 'test_database.db' file using queries from 'resource/sqls' directory. 
After test 'test_database.db' file is cleared.
- Test resetDB creates empty database with given values. Test is creating new 
'test_database.db' file using queries from 'resource/sqls' directory. 
After test 'test_database.db' file is cleared.

**SupplierDaoSqlite:**

_TESTS:_

- Before each test new connection is set and 'test_database.db' file is created.
This file i filled with given information from 'resource/sqls' directory.
- After each test connection is closed.
- Test add method by created new Supplier instance and passing it as param to add method.
Statement is executed on connection with 'test_database.db' file.
- Test find method by getting out information about supplier from row with id = 1.
- Test find method returns null with incorrect id param given.
- Test remove method with 'id' as param by executing delete statement on connection with 'test_database.db' file
and asserting 'false' with find method using that 'id'.
- Test getAll method by asserting count of the rows in 'suppliers' table at 'test_database.db'
with size of the returned list from getAll method and checking first and the last record pulled from 'test_database.db'.

_COMMENTS:_

- SupplierDaoSqlite class should have field with DatabaseConnection object.
- There should be a method to set 'pathToDatabase" param on DatabaseConnection object.
- Methods should have single responsibility. There should be methods for creating statement
and separated for executing that statement.

**BaseModel:**

_TESTS:_

- 

**Product:**


_REFACTOR:_

- Constructor method assigns parameters to attributes, setters were removed.
- setPrice now validates price, if it is below 0 it throws Illegal Argument exception.
- setDefaultPrice now validates price, if it is below 0 it throws Illegal Argument exception.

_TESTS:_

- Constructor method without provided Supplier and ProductCategory objects.
- Constructor method with incorrect Currency code.
- Constructor without provided id.
- Constructor with provided all values.
- All setters and getters including minus values passed into setPrice/setDefaultPrice.
- Equals method.

_COMMENTS:_
- SetPrice and SetDefaultPrice should have validation of provided price value (ensure that no minus values are provided).
- Constructor should not contain setters.


**Cart:**


_REFACTOR:_

- ChangeQuantity method now validates provided new quantity ensuring that 1 is the lowest quantity.

_TESTS:_

- Test add product by passing new product.
- Test remove product by passing id number of existing product (6).
- Test change quantity by passing new value also negative value.
- Test size.
- Test GetSum by adding 3 test products to the cart.
- Test GetIndexIfExists.

_COMMENTS:_
- ChangeQuantity should ensure that quantity is above 0. 

**ProductDaoSqlite:**

_TESTS:_

- Test add new product.
- Test find product by using existing id.
- Test find product by using non-existing id.
- Test getAll, getAllBySupplier, getAllByProductCategory, getByName.


**ProductDaoSqlite:**

_TESTS:_

- Test add new product.
- Test find product by using existing id.
- Test find product by using non-existing id.
- Test getAll, getAllBySupplier, getAllByProductCategory, getByName.


**ProductController**

_TESTS:_

- Test getAllProductsBySearch given test product name existing in test database.
- Test add product by passing test product.
- Test renderAddForm by checking returning class.
- Test getBySupplier by passing as argument test supplier existing in database.
- Test getByProductCategory by passing as argument test product category existing in database.
- Test getByProductName by providing test product name (one of the products existing in test database).
- Test getProductJsonById by passing test id (belonging to existing product from test database) and checking whether 
returned string contains name of test product (owning test id).
