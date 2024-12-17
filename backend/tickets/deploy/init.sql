create type venue_type as enum ('BAR', 'CINEMA', 'STADIUM');

create type ticket_type as enum ('VIP', 'BUDGETARY', 'CHEAP');

create table coordinates
(
    id  bigserial            primary key,
    x   double precision,
    y   double precision     not null
);

create table addresses
(
    id          bigserial   primary key,
    zip_code    text        constraint zip_code_check check (char_length(zip_code) >= 10)
);

create table venues
(
    id              bigserial       primary key,
    name            text            not null        constraint v_name_check check (char_length(name) > 0),
    capacity        bigint          not null        constraint capacity_check check (capacity >= 1),
    type            venue_type      not null,
    address_id      bigint                          references addresses (id)
);

create table persons
(
    id              bigserial           primary key,
    username        text                not null        constraint p_name_check check (char_length(username) > 0),
    password        text                not null        constraint p_password_check check (char_length(password) > 0),
    balance         double precision    not null
);

create table tickets
(
    id              bigserial           primary key,
    name            text                not null        constraint v_name_check check (char_length(name) > 0),
    coordinates_id  bigint                              references coordinates (id),
    creation_date   date                not null        default now(),
    is_sold         boolean,
    price           double precision                    constraint price_check check (price >= 0),
    type            ticket_type         not null,
    venue_id        bigint                              references venues (id),
    person_id       bigint                              references persons (id)
);
