create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null -- WHAT!? NOT ENCRYPTED!? ;-)
);

create table Address
(
    address_id  bigint              not null primary key,
    user_id     bigint              not null unique references User(id),
    address1    varchar(255)        not null,
    address2    varchar(255),
    city        varchar(255)        not null,
    state       varchar(100)        not null,
    postal      varchar(10)         not null
);

insert into User
    (id, firstName, lastName, username, password)
values
    (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123'),
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234');

insert into Address
    (address_id, user_id, address1, address2, city, state, postal)
values
    (1, 1, '1060 W Addison St', '', 'Chicago', 'IL', '60613'),
    (2, 2, '8500 South Burley Avenue', '', 'Chicago', 'Illinois', '60017');

