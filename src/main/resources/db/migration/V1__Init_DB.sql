create sequence hibernate_sequence start 1 increment 1;
create sequence user_jpa_sequence start 3 increment 1;
create sequence coctail_jpa_sequence start 12 increment 1;
create sequence ingredient_jpa_sequence start 265 increment 1;

create table coctail (
	id numeric(19, 2) not null,
	name varchar(255) not null,
	image varchar(255),
	description varchar(2048),
	user_id numeric(19, 2),
	primary key (id)
);

create table coctail_ingredient (
	volume float4 not null,
	unit varchar(255),
	coctail_id numeric(19, 2) not null,
	ingredient_id numeric(19, 2) not null,
	primary key (coctail_id, ingredient_id)
);

create table ingredient (
	id numeric(19, 2) not null,
	name varchar(255),
	ingredient_group varchar(255),
	image varchar(255),
	description varchar(2048),
	user_id numeric(19, 2),
	primary key (id)
);

create table usr (
	id numeric(19, 2) not null,
	active boolean not null,
	password varchar(255) not null,
	username varchar(255) not null,
	email varchar(255),
	activation_code varchar(255),
	primary key (id)
);

create table usr_bar_ingredients (
    user_id numeric(19, 2) not null,
    bar_ingredients_id numeric(19, 2) not null
);

alter table if exists coctail
add constraint coctail_user_fk
foreign key (user_id)
references usr;

alter table if exists coctail_ingredient
add constraint coctail_to_coctail_ingredient_fk
foreign key (coctail_id)
references coctail;

alter table if exists coctail_ingredient
add constraint ingredient_to_coctail_ingredient_fk
foreign key (ingredient_id)
references ingredient;

alter table if exists ingredient
add constraint ingredient_user_fk
foreign key (user_id)
references usr;

alter table if exists usr_bar_ingredients
add constraint usr_bar_ingredients_ingredient_fk
foreign key (bar_ingredients_id)
references ingredient;

alter table if exists usr_bar_ingredients
add constraint usr_bar_ingredients_usr_fk
foreign key (user_id)
references usr;