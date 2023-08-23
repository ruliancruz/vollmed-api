CREATE TABLE patients
(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(100) not null,
    cpf varchar(11) not null unique,
    street varchar(100) not null,
    number varchar(100) not null,
    complement varchar(100) not null,
    district varchar(100) not null,
    city varchar(100) not null,
    uf char(2) not null,
    cep varchar(9) not null,

    primary key(id)
);