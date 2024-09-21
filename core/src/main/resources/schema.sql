create database if not exists booking_system;

use booking_system;

create table if not exists `user` (
    id bigint unsigned primary key not null auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    created datetime not null,
    deleted bit default 0
);

create table if not exists `auditorium` (
    id bigint unsigned primary key not null auto_increment,
    `name` varchar(255) unique not null
);

create table if not exists `auditorium_size` (
    id bigint unsigned primary key not null auto_increment,
    auditorium_id bigint unsigned not null,
    `row` mediumint unsigned not null,
    seats mediumint unsigned not null,
    constraint fk_auditorium_auditoriumsize foreign key (auditorium_id) references auditorium(id)
);

create table if not exists `event` (
    id bigint unsigned primary key not null auto_increment,
    `name` varchar(255) not null,
    auditorium_id bigint unsigned not null,
    `start_date` datetime not null,
    `end_date` datetime not null,
    deleted datetime,
    constraint fk_auditorium_event foreign key (auditorium_id) references auditorium(id)
);

create table if not exists `seat_booking` (
    id bigint unsigned primary key not null auto_increment,
    `row` mediumint unsigned not null,
    seat mediumint unsigned not null,
    auditorium_id bigint unsigned not null,
    user_id bigint unsigned not null,
    event_id bigint unsigned not null,
    constraint fk_auditorium_seatbooking foreign key (auditorium_id) references auditorium(id),
    constraint fk_user_seatbooking foreign key (user_id) references user(id),
    constraint fk_event_seatbooking foreign key (event_id) references `event`(id)
);