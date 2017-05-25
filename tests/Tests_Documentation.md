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