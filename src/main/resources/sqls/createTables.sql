CREATE TABLE IF NOT EXISTS `products` ( id integer PRIMARY KEY AUTOINCREMENT,
                          name TEXT NOT NULL,
                          defaultPrice REAL NOT NULL,
                          currency CHAR(10),
                          description CHAR(50),
                          categoryId  INTEGER,
                          supplierId INTEGER
);

create table IF NOT EXISTS `product_categories` (
  id integer PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(16),
  department VARCHAR(50),
  description TEXT
);

create table IF NOT EXISTS `suppliers` (
  id integer PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50),
  description TEXT
);
