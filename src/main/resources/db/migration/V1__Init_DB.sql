create sequence hibernate_sequence start 1 increment 1;

create table coctail (
	id numeric(19, 2) not null,
	description varchar(2048),
	image varchar(255),
	name varchar(255),
	user_id numeric(19, 2),
	primary key (id)
);

create table coctail_ingredient (
	volume float4 not null,
	coctail_id numeric(19, 2) not null,
	ingredient_id numeric(19, 2) not null,
	primary key (coctail_id, ingredient_id)
);

create table ingredient (
	id numeric(19, 2) not null,
	description varchar(2048),
	image varchar(255),
	name varchar(255),
	user_id numeric(19, 2),
	primary key (id)
);

create table usr (
	id numeric(19, 2) not null,
	active boolean not null,
	password varchar(255),
	username varchar(255),
	primary key (id)
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