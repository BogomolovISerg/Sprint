create table users (
  id                    bigserial,
  username              varchar(20) not null unique,
  password              varchar(20) not null,
  email                 varchar(20) unique,
  primary key (id)
);

create table roles (
  id                    bigserial,
  name                  varchar(20) not null,
  primary key (id)
);

create table users_roles (
  user_id               bigserial not null,
  role_id               bigserial not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('USER'), ('MANAGER'), ('ADMIN'), ('SUPERADMIN');

insert into users (username, password, email)
values
('user', '123', 'user@gmail.ru'),
('manager', '123', 'manager@mail.ru'),
('admin', '123', 'admin@mail.ru'),
('superadmin', '123', 'superadmin@mail.ru');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 3),
(4, 4);

create table products (
    id                    int,
    title                 varchar(255) not null,
    price                 decimal(6,2) not null,
    primary key (id));

insert into products (title, price)
values
('Чай', 100),
('Кофе', 200),
('Лимонад', 90),
('Сок', 250);