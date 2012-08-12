insert into User(ID, EMAIL, NAME, PASSWORD, USERNAME) values (1,'jonnysgomes@mail.com','Jonnys Gomes', '123456', 'jonnysgomes');
insert into User(ID, EMAIL, NAME, PASSWORD, USERNAME) values (2,'brunamota@mail.com','Bruna Mota', '123456', 'brunamota');

insert into Role(ID, NAME) values (1,'ROLE_ADMINISTRADOR');
insert into Role(ID, NAME) values (2,'ROLE_USUARIO');

insert into User_Role (User_id, roles_id) VALUES (1,1);
insert into User_Role (User_id, roles_id) VALUES (2,2);

