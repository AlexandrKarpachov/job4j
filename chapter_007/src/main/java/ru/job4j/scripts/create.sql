
createdb traker;

create table Roles (
    id serial primary key,
    role_name varchar(128)
);

create table Users (
    id serial primary key,
    user_name varchar(128),
    role_id integer references Roles(id)
);

create table Rules (
    id serial primary key,
    some_access boolean,
    description text
);

create table RoleToRules (
    id serial primary key,
    role_id integer references Roles(id),
    rule_id integer references Rules(id)
);

create table Category (
    category varchar(200) primary key,
    description text
);

create table Status (
    id serial primary key,
    status varchar (50)
);

create table Item (
    id serial primary key,
    item_name varchar(128),
    user_id integer references Users(id),
    category varchar(200) references Category(category),
    status_id integer references Status(id)
);

create table Comments (
    id serial primary key,
    description text,
    item_id integer references Item(id)
);

create table Files (
    id serial primary key,
    file_path varchar(2000),
    item_id integer references Item(id)
);


insert into Roles (role_name) values
    ('Admin'),
    ('User');

insert into Rules (some_access, description) values
    (true, 'admin rules description'),
    (false, 'user rule description');

insert into RoleToRules (role_id, rule_id) values
    (1, 1), (2, 2);

insert into Category (category, description) values
    ('registration', 'all problems with regitration'),
    ('suggestion', 'some user suggestions'),
    ('common', 'common problems');

insert into Status (status) values
    ('close'),
    ('open');

insert into Users (user_name, role_id) values
    ('Daniel Defo', 1),
    ('Denzel Washington', 2);

insert into Item (item_name, user_id, category, status_id) values
    ('first item', 1, 'common', 2),
    ('some suggestion', 2, 'suggestion', 1);

insert into Comments (description, item_id) values
    ('some problem comment', 1),
    ('some suggestion description', 2);

insert into Files (file_path, item_id) values
    ('file path', 2);


