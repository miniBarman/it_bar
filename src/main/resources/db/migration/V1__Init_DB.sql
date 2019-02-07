create sequence hibernate_sequence start 1 increment 1;

create table coctail (
	id numeric(19, 2) not null,
	description varchar(255),
	image varchar(255),
	name varchar(255) not null,
	user_id numeric(19, 2), primary key (id)
);

create table usr (
	id numeric(19, 2) not null,
	active boolean not null,
	password varchar(255) not null,
	username varchar(255) not null,
	primary key (id)
);

alter table if exists coctail
  add constraint coctail_user_fk
  foreign key (user_id)
  references usr;