create table if not exists role
(
    id                  serial primary key,
    role                text not null unique
);

create table if not exists users
(
    id                  serial primary key,
    mail                text not null unique,
    password            text not null,
    role_id             int not null
        references role (id)
);

create table if not exists product_type
(
    id                  serial primary key,
    type                text not null
);

create table if not exists product
(
    id                  serial primary key,
    link                text not null,
    size                text,
    color               text,
    description         text,
    product_type_id     int
        references product_type (id)
);

-- КОРЗИНА
create table if not exists cart
(
    id                  serial primary key,
    user_id             int not null
        references users (id),
    product_id          int not null
        references product (id),
    price               float
);

-- ЗАКАЗЫ
create table if not exists orders
(
    id                  serial primary key,
    user_id             int not null
        references users (id),
    condition           text,
    create_date         timestamp
);

-- Связь товаров в заказах
create table if not exists products_in_order
(
    id                  serial primary key,
    order_id            int not null
        references orders (id),
    product_id          int not null
        references product (id),
    condition           text,
    price               float
);
