drop database if exists leaderboard;

create database leaderboard;

use leaderboard;

create table highscore (

    id int auto_increment,
    name varchar(64) not null,
    score int not null,

    primary key (id)
);