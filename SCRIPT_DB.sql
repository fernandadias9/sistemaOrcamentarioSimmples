drop database if exists dbsenhorfinancas;

create database dbsenhorfinancas;

use dbsenhorfinancas;

create table usuario (
idusuario int not null primary key auto_increment,
nome varchar(255),
cpf varchar(11) unique,
email varchar(255),
datanascimento date,
login varchar(255),
senha varchar(255)
);


create table receita (
idreceita int not null primary key auto_increment,
idusuario int, foreign key (idusuario) references usuario (idusuario),
descricao varchar(255),
valor decimal(10,2),
datareceita date
);


create table despesa (
iddespesa int not null primary key auto_increment,
idusuario int, foreign key (idusuario) references usuario (idusuario),
descricao varchar(255),
valor decimal(10,2),
datavencimento date,
datapagamento date
);



insert into usuario (nome, cpf, email, datanascimento, login, senha) 
values ('Adriano', '01234567890', 'adriano@gmail.com', '1978-02-24', 'adriano', 'adriano');

select * from usuario;

select * from receita;
select * from despesa;

-- create view vreceita as
-- select sum(valor) as receita, 0 as despesa, month(datareceita) as mes from receita where idusuario = 1 and year(datareceita) = 2023 group by month(datareceita);
-- create view vdespesa as
-- select 0 as receita, sum(valor) as despesa, month(datavencimento) as mes from despesa where idusuario = 1 and year(datavencimento) = 2023 group by month(datavencimento);

-- select ifnull(vreceita.receita, 0), ifnull(vdespesa.despesa, 0), vdespesa.mes from vreceita right join vdespesa on vreceita.mes = vdespesa.mes order by mes;

select * from vdespesa;

select sum(receita), sum(despesa), relatorio.mes from (select sum(valor) as receita, 0 as despesa, month(datareceita) as mes from receita where idusuario = 1  and year(datareceita) = 2023  group by month(datareceita) union select 0 as receita, sum(valor) as despesa, month(datavencimento) as mes from despesa where idusuario = 1 and year(datavencimento) = 2023 group by month(datavencimento)) relatorio group by mes order by mes;
