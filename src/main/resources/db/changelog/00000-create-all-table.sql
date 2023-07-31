create table chat
(
    id   bigserial    not null,
    name varchar(255) not null unique,
    primary key (id)
);

create table company
(
    id   bigserial    not null,
    name varchar(255) not null unique,
    primary key (id)
);

create table company_locales
(
    company_id  bigint       not null,
    description varchar(255),
    lang        varchar(255) not null,
    primary key (company_id, lang)
);

create table payment
(
    amount      bigint,
    id          bigserial not null,
    receiver_id bigint,
    primary key (id)
);

create table user_chat
(
    chat_id bigint,
    id      bigserial not null,
    user_id bigint,
    primary key (id)
);

create table users
(
    birth_date date         not null,
    company_id bigint,
    id         bigserial    not null,
    first_name varchar(255) not null,
    role       varchar(255) check (role in ('USER', 'ADMIN')),
    user_name  varchar(255) not null,
    primary key (id)
);

alter table if exists company_locales
    add constraint FK1t4yec0wotxsdkr598xnav99q
        foreign key (company_id)
            references company;

alter table if exists payment
    add constraint FKb2u6muqxjvdfbib5u91afawcy
        foreign key (receiver_id)
            references users;

alter table if exists user_chat
    add constraint FKlr24iyc3pugqj18ybujh6hqmj
        foreign key (chat_id)
            references chat;

alter table if exists user_chat
    add constraint FK77xw0w87qhmewpoa735qabj4j
        foreign key (user_id)
            references users;

alter table if exists users
    add constraint FKbwv4uspmyi7xqjwcrgxow361t
        foreign key (company_id)
            references company;
