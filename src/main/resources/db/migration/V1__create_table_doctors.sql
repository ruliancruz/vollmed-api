create table doctors
(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(15) not null,
    crm varchar(8) not null unique,
    specialization varchar(100) not null,
    street varchar(100) not null,
    number varchar(20) not null,
    complement varchar(100) not null,
    district varchar(100) not null,
    city varchar(100) not null,
    uf char(2) not null,
    cep varchar(9) not null,

    primary key(id)
);