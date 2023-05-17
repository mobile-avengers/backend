-- ROLES
insert into role values (1, 'USER');
insert into role values (2, 'ADMIN');

-- USERS
insert into users values (DEFAULT, 'chebyrashka@mail.ru', '4321', 2);
insert into users values (DEFAULT, 'test_user@gmail.ru', '1111', 1);

-- PRODUCTS TYPES
insert into product_type values (1, 'clothes');
insert into product_type values (2, 'shoes');