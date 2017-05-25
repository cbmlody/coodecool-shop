**TEST DOCUMENTATIONS**


**Main:**

_REFACTOR:_

- After App.run() App.getApp().setConnection method is needed to establish connection
with given String pathToDatabase param.
- App.getApp().closeConnection() method is moved to 'try' block to handle eventually
SQLException.


**App:**

_REFACTOR:_

- Field with DatabaseConnection instance added.
- setConnection method added which establish connection from given String pathToDatabase param.
- Handling eventually SQLException is moved to Main.java.


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

- Test setId and getId by using that methods on BaseModel instance.
- Test setName and getName by using that methods on BaseModel instance.
- Test setDescription and getDescription by using that methods on BaseModel instance.
- Test toString method by asserting expected String value with result of the method.


**FileReader:**

_TESTS:_

- Test getString method with invalid 'pathToResource' param returns empty String object.
- Test getString method with correct 'pathToResource' param returns expected String value.


**JsonTransformer:**

_REFACTOR:_

- Comment out method not used in application.

_TESTS:_

- Test render method returns String with JSON format for given Product object.


