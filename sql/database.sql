-- download mysqlworkbench and import and run this file
drop database if exists bagsystem;
create database bagsystem;
use bagsystem;

CREATE TABLE `users` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `username` VARCHAR(20) NOT NULL UNIQUE,
    `profile_img` VARCHAR(255) DEFAULT 'default.png',
    `password` VARCHAR(250) NOT NULL,
    `date_updated` DATETIME ON UPDATE CURRENT_TIMESTAMP,
    `date_created` DATETIME DEFAULT NOW(),
    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AS_CS;

create table `groups` (
	`id` integer not null auto_increment,
    `name`  varchar(20) unique not null,
	`created_by` int default null,
	`date_updated` datetime on update current_timestamp,
	`date_created` datetime default NOW(),
	foreign key(`created_by`) references `users`(`id`) ON DELETE SET null,
	primary key(`id`)
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs;

create table `user_groups`(
	`id`	integer not null auto_increment,
	`user_id` int not null,
	`group_id` int not null,
	primary key(`id`),
    foreign key(`user_id`) references users(`id`) ON DELETE cascade,
	foreign key(`group_id`) references `groups`(`id`) ON DELETE cascade
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs;

create table `permissions` (
	`id` integer not null auto_increment,
	`name` varchar(255) unique not null,
	`created_by` int default null,
	`date_updated` datetime on update current_timestamp,
	`date_created` datetime default NOW(),
    foreign key(`created_by`) references `users`(`id`) ON DELETE SET NULL,
	primary key(`id`)
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs;

create table `groups_permissions`(
	`id`	integer not null auto_increment,
	`group_id` int not null,
	`permission_id` int not null,
	primary key(`id`),
    foreign key(`permission_id`) references permissions(`id`) ON DELETE cascade,
	foreign key(`group_id`) references `groups`(`id`) ON DELETE cascade
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs;

create table `products` (
 	`id` integer not null auto_increment,
    `name`  varchar(20) not null unique,
    `description` text not null,
    `image` varchar(255) default null,
	`added_by` int default null,
    `updated_by` int default null,
    `date_updated` datetime on update current_timestamp,
    `date_created` datetime default NOW(),
    foreign key(`added_by`) references `users`(`id`) ON DELETE SET NULL,
	foreign key(`updated_by`) references `users`(`id`) ON DELETE SET NULL,
    primary key(`id`)
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs;

create table `stock` (
 	`id` integer not null auto_increment,
    `product_id` integer not null,
	`quantity` integer not null,
    `buying_price` float not null,
    `color` varchar(20) not null ,
    `size` varchar(20) not null,
	`added_by` int default null,
	`updated_by` int default null,
    `date_updated` datetime on update current_timestamp,
    `date_created` datetime default NOW(),
    primary key(`id`),
	foreign key(`added_by`) references `users`(`id`) ON DELETE SET NULL,
	foreign key(`updated_by`) references `users`(`id`) ON DELETE SET NULL,
    foreign key(`product_id`) references products(`id`) ON DELETE cascade
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs;

create table transactions(
	`id` integer not null auto_increment,
	`stock_id` integer not null,
	`quantity` integer default null,
    `buying_price` float not null,
    `selling_price` float not null,
	`added_by` int default null,
	`updated_by` int default null,
	`date_updated` datetime on update current_timestamp,
	`date_created` datetime default NOW(),
	primary key(`id`),
	foreign key(`added_by`) references `users`(`id`) ON DELETE SET NULL,
	foreign key(`updated_by`) references `users`(`id`) ON DELETE SET NULL,
	foreign key(`stock_id`) references stock(`id`) ON DELETE cascade
)engine= InnoDB default charset = utf8mb4 collate= utf8mb4_0900_as_cs; 
create view stock_list as select products.name as product_name, products.description as product_description,products.image as image,stock.color as color,stock.id as stock_id, stock.size as size, stock.quantity as quantity, stock.buying_price as buying_price, stock.date_updated
as date_updated,stock.date_created as date_created from stock inner join products on stock.product_id = products.id;
create view transaction_list as select products.name as product_name,transactions.id as transaction_id, stock.color as color, stock.size as size, transactions.quantity as quantity, transactions.buying_price as buying_price, transactions.selling_price as selling_price, transactions.added_by as added_by, transactions.updated_by as updated_by, transactions.date_created as date_created, transactions.date_updated as
date_updated from transactions inner join stock on transactions.stock_id = stock.id inner join products on products.id = stock.product_id;
create view user_groups_view as select users.username, `groups`.name , user_groups.user_id, user_groups.group_id from users inner join `groups` inner join user_groups on users.id = user_groups.user_id and `groups`.id=user_groups.group_id;
create view group_permission_view as select groups.name as group_name, permissions.name as permission_name, groups_permissions.group
n `groups` on groups_permissions.group_id = `groups`.id inner join permissions on groups_permissions.perm
ission_id = permissions.id;
create view user_group_permission_view as select permissions.name as permission_name, users.username, `groups`.name as group_name from groups_permissions inner join permissions on groups_permissions.permission_id = permissions.id inner join `groups` on `groups`.id = groups_permissions.group_id inner join user_groups on user_groups.group_id = `groups`.id inner join users on users.id = user_groups.user_id;