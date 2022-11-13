create table devices
(
    id            varchar   not null,
    version       integer   not null,
    created_at    timestamp not null,
    updated_at    timestamp not null,
    name          varchar   not null,
    available     boolean default true,
    deleted       boolean default false,
    last_order_id varchar,
    details       varchar,
    primary key (id)
);

create table orders
(
    id          varchar(255) not null,
    booked_at   timestamp    not null,
    released_at timestamp,
    author      varchar(255) not null,
    device_id   varchar(255),
    primary key (id)
);

alter table orders
    add constraint device_reference foreign key (device_id) references devices (id);

alter table devices
    add constraint last_order_reference foreign key (last_order_id) references orders (id);