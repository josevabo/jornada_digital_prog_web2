
-- USUARIOS todos com senha 1234
insert into usuario (nome, account_expired, account_locked, active, cpf, credentials_expired, email, password, roles, telefone, username) VALUES ('Funcionario',false,false,true,'88385302034',false,'user@funcionario.com' ,'$2a$10$i1EA6PcrknRyDNyUJGx0k.INJT5LOMP5c5ADnrpO7hGmXWBPSXbZu','FUNCIONARIO','99999999','user_funcionario');
insert into usuario (nome, account_expired, account_locked, active, cpf, credentials_expired, email, password, roles, telefone, username) VALUES ('Cliente',false,false,true,'12345678909',false,'user@cliente.com' ,'$2a$10$i1EA6PcrknRyDNyUJGx0k.INJT5LOMP5c5ADnrpO7hGmXWBPSXbZu','CLIENTE','99999999','user_cliente');
insert into usuario (nome, account_expired, account_locked, active, cpf, credentials_expired, email, password, roles, telefone, username) VALUES ('Admin',false,false,true,'73189091005',false,'user@admin.com' ,'$2a$10$i1EA6PcrknRyDNyUJGx0k.INJT5LOMP5c5ADnrpO7hGmXWBPSXbZu','ADMIN','99999999','user_admin');


-- CLIENTES

insert into cliente (nome, cpf, status, data_nascimento) values ('Jose cliente', '12345678909', 1, '2000-01-01');
insert into cliente (nome, cpf, status, data_nascimento) values ('Joao cliente', '73189091005', 1, '1999-01-01');

-- CONTAS
insert into conta (cliente_id, data_abertura, saldo) values (1, '2020-01-01', 100.00);

