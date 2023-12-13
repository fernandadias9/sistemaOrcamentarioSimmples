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
