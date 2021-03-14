CREATE TABLE role (
    id int PRIMARY KEY,
    role_type varchar(255) not null
);

CREATE TABLE actor (
    user_id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(40) not null UNIQUE,
    password varchar(255) not null,
    stream varchar(30) not null,
    email varchar(255) not null UNIQUE,
    role_id int
);

insert into role (id, role_type) values (1,'FRONTEND_SUPERVISOR');
insert into role (id, role_type) values (2,'BACKEND_SUPERVISOR');
insert into role (id, role_type) values (3,'TESTING_SUPERVISOR');
insert into role (id, role_type) values (4,'PROJECT_SUPERVISOR');
insert into role (id, role_type) values (999,'ADMIN');