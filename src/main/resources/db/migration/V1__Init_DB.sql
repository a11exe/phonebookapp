create table usr (
  id            serial,
  name          varchar(255) not null,
  primary key (id)
);
create table phones (
  user_id       int8,
  phoneNumber   varchar(255),
  id            serial,
  primary key (id)
);

alter table if exists phones
  add constraint phones_user_fk foreign key (user_id) references usr;