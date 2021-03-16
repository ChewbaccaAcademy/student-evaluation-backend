CREATE TABLE role (
    id int PRIMARY KEY,
    role_type varchar(255)
);

CREATE TABLE actor (
    id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(40) not null UNIQUE,
    password varchar(60) not null,
    stream varchar(8),
    email varchar(255) not null UNIQUE,
    role_id int
);

insert into role (id, role_type) values (1,'ADMIN');
insert into role (id, role_type) values (2,'USER');

insert into actor (id, username, password, stream, email, role_id) values (1, 'MrKrabs', '$2y$12$MbSLE0IqjFLL6vFiZ6yKquQ6h7RTe2eEVBVb.Lbivw82U4sWqlC9a', '', 'mrkrabs@teachers.com', 1);
insert into actor (id, username, password, stream, email, role_id) values (2, 'Spongebob', '$2y$12$l/iyp/VVk0qwcO1CpX1lA.wlPPXmslI6QpFMlNMTrLF0o.PQQtYjy', 'FRONTEND', 'spongy@teachers.com', 2);
insert into actor (id, username, password, stream, email, role_id) values (3, 'Patrick', '$2a$12$o1bZipWsTto5KttQsiX7o.Q0XFn94JQhKfXjZjx.q/R7jXcUfuMpC', 'BACKEND', 'patrick@teachers.com', 2);
insert into actor (id, username, password, stream, email, role_id) values (4, 'Sandy', '$2a$12$OdcjHkMW67SCEUSGbPJ8xuWo1e1ScyNsAirgO6i8P2fD8.Q9VE4n.', 'TESTING', 'sandy@teachers.com', 2);
insert into actor (id, username, password, stream, email, role_id) values (5, 'Squidward', '$2a$12$cHgC/O1QjDBJOxa0GCR40uJ3hyF1Ccn6vLlydBvobj6VnRa18L2.a', 'PROJECT', 'squid@teachers.com', 2);