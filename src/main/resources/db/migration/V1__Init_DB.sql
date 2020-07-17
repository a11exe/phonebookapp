create table usr (
  id      int8 not null,
  name    varchar(255) not null,
  primary key (id)
);
create table phones (
  user_id int8 not null,
  phone   varchar(255),
  id      int8 not null,
  primary key (id)
);

alter table if exists phones
  add constraint phones_user_fk foreign key (user_id) references usr;