create sequence hibernate_sequence start 1 increment 1;

create table coctail (
	id numeric(19, 2) not null,
	description varchar(255),
	image varchar(255),
	name varchar(255) not null,
	user_id numeric(19, 2),
	primary key (id)
);

create table usr (
	id numeric(19, 2) not null,
	active boolean not null,
	password varchar(255) not null,
	username varchar(255) not null,
	primary key (id)
);

create table ingredient (
	id numeric(19, 2) not null,
	description varchar(255),
	image varchar(255),
	name varchar(255) not null,
	user_id numeric(19, 2),
	primary key (id)
);

create table coctail_ingredient (
    coctail_id numeric(19, 2) not null,
    ingredient_id numeric(19, 2) not null,
    value float4,
    primary key (coctail_id, ingredient_id)
);

alter table if exists coctail
  add constraint coctail_user_fk
  foreign key (user_id)
  references usr;

alter table if exists ingredient
  add constraint ingredient_user_fk
  foreign key (user_id)
  references usr;

alter table if exists ingredient
  add constraint ingredient_user_fk
  foreign key (user_id)
  references usr;

alter table if exists coctail_ingredient
    add constraint coctail_to_ingredient_fk
    foreign key (coctail_id)
    references coctail;

alter table if exists coctail_ingredient
    add constraint ingredient_to_fk
    foreign key (ingredient_id)
    references ingredient;