DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `suppliers`;
DROP TABLE IF EXISTS `product_categories`;


CREATE TABLE `products` ( id integer PRIMARY KEY AUTOINCREMENT,
                          name TEXT NOT NULL,
                          defaultPrice REAL NOT NULL,
                          currency CHAR(10),
                          description CHAR(50),
                          categoryId  INTEGER,
                          supplierId INTEGER
);

create table `product_categories` (
  id integer PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(16),
  department VARCHAR(50),
  description TEXT
);

create table `suppliers` (
  id integer PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50),
  description TEXT
);

insert into suppliers (name, description) values ('Apotex Corp.', 'cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque');
insert into suppliers  (name, description) values ('Kramer Novis', 'diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis');
insert into suppliers  (name, description) values ('Purminerals', 'ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero');
insert into suppliers  (name, description) values ('AuroMedics Pharma LLC', 'scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at');
insert into suppliers  (name, description) values ('MATERNAL SCIENCE, LLC', 'porta volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla nunc purus phasellus in felis donec semper');
insert into suppliers  (name, description) values ('Rite Aid', 'nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo');
insert into suppliers  (name, description) values ('Smith & Nephew, Inc.', 'posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam');
insert into suppliers  (name, description) values ('Hospira, Inc', 'felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec');
insert into suppliers  (name, description) values ('China Ningbo Shangge Cosmetic Technology Corp.', 'felis donec semper sapien a libero nam dui proin leo odio porttitor id consequat in consequat ut');
insert into suppliers  (name, description) values ('Aloe Care International, LLC', 'odio donec vitae nisi nam ultrices libero non mattis pulvinar nulla pede ullamcorper augue a');

insert into product_categories (name, department, description) values ('Skin Care', 'Health', 'auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio');
insert into product_categories (name, department, description) values ('Baby', 'Automotive', 'platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit');
insert into product_categories (name, department, description) values ('Diet&Fitness', 'Electronics', 'urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate');
insert into product_categories (name, department, description) values ('Pharmacy', 'Clothing', 'at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed');
insert into product_categories (name, department, description) values ('Household', 'Tools', 'viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper');
insert into product_categories (name, department, description) values ('Baby', 'Toys', 'nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis');
insert into product_categories (name, department, description) values ('Personal Health', 'Music', 'ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est');
insert into product_categories (name, department, description) values ('Baby', 'Automotive', 'scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec');
insert into product_categories (name, department, description) values ('Vitamins', 'Games', 'quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum');
insert into product_categories (name, department, description) values ('Hair Products', 'Books', 'est risus auctor sed tristique in tempus sit amet sem fusce consequat nulla nisl nunc nisl duis');

INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Dr.Jart CC Essence Balm 02 Medium - Deep', 127.71, 'CNY', 'rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta', 9, 7);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Atenolol', 764.85, 'IDR', 'turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede', 1, 5);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Regular Strength Aspirin EC', 163.49, 'EUR', 'tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at', 3, 1);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Mupirocin', 473.31, 'EUR', 'metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci', 7, 5);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Natralia Eczema and Psoriasis', 966.85, 'IDR', 'non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi', 8, 8);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Metoprolol Tartrate', 44.29, 'IDR', 'mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus', 3, 7);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('topcare severe sinus congestion', 353.62, 'PLN', 'dolor sit amet consectetuer adipiscing elit proin risus praesent lectus vestibulum quam', 5, 1);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('No7 Lift and Luminate Foundation Sunscreen Broad Spectrum SPF 15 Honey', 41.74, 'BRL', 'nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit', 10, 7);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Divalproex Sodium', 931.7, 'PHP', 'primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra', 1, 10);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('ED A-HIST', 382.93, 'EUR', 'non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in', 3, 6);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('TIME ZONE', 599.73, 'PLN', 'vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl', 4, 5);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Walgreens Sunscreen', 202, 'PLN', 'id lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi', 4, 8);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('AMITRIPTYLINE HYDROCHLORIDE', 248.48, 'PHP', 'luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh', 8, 10);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Apanol', 296.3, 'BRL', 'proin leo odio porttitor id consequat in consequat ut nulla sed accumsan felis ut at', 8, 6);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('LBEL NATURAL FINISH MOISTURIZING FOUNDATION SPF 25', 740.7, 'EUR', 'donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam', 8, 7);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Diazepam', 623.51, 'MXN', 'justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi', 7, 4);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Triple Antibiotic', 93.84, 'GBP', 'nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices', 10, 10);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('DESPIGMEN PROTECTIVE 25', 788.31, 'EUR', 'ipsum praesent blandit lacinia erat vestibulum sed magna at nunc commodo placerat', 5, 3);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Mastic Dent Whitening', 764.78, 'EUR', 'ac nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit ligula in lacus curabitur at ipsum', 6, 3);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Ibuprofen', 130.57, 'EUR', 'primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor', 10, 1);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Diclofenac Sodium', 733.74, 'CZK', 'ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu', 5, 6);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Equaline Naproxen Sodium', 891.49, 'CNY', 'nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit', 8, 5);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('ClomiPHENE Citrate', 520.4, 'SEK', 'sagittis nam congue risus semper porta volutpat quam pede lobortis ligula', 2, 2);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('pain relief', 879.02, 'CNY', 'elit proin risus praesent lectus vestibulum quam sapien varius ut', 3, 1);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Xylocaine', 615.95, 'PLN', 'sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis turpis', 8, 1);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Benzonatate', 522.01, 'EUR', 'dolor morbi vel lectus in quam fringilla rhoncus mauris enim', 3, 7);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('ENTEX LQ', 8.64, 'RUB', 'dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti', 1, 3);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Secura Protective', 172.84, 'SEK', 'eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris', 1, 9);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('INTENSE CARE GALACTOMYCES WHITENING', 684.83, 'USD', 'ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero', 10, 4);
INSERT INTO products (name, defaultPrice, currency, description, categoryId, supplierId) VALUES ('Being Well stomach relief', 773.33, 'CNY', 'volutpat dui maecenas tristique est et tempus semper est quam', 3, 6);