# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table advised_user (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  dni                           varchar(255),
  city                          varchar(255),
  email                         varchar(255),
  id_advised_user               integer,
  consultant_id                 bigint,
  constraint pk_advised_user primary key (id)
);

create table consultant (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  dni                           varchar(255),
  city                          varchar(255),
  email                         varchar(255),
  id_consultant                 integer,
  constraint pk_consultant primary key (id)
);

create table profit (
  id                            bigint auto_increment not null,
  quantity                      float,
  description                   varchar(255),
  id_profit                     integer,
  user_id                       bigint,
  creation_date                 datetime(6) not null,
  constraint pk_profit primary key (id)
);

create table spending (
  id                            bigint auto_increment not null,
  quantity                      float,
  description                   varchar(255),
  id_spending                   integer,
  user_id                       bigint,
  creation_date                 datetime(6) not null,
  constraint pk_spending primary key (id)
);

alter table advised_user add constraint fk_advised_user_consultant_id foreign key (consultant_id) references consultant (id) on delete restrict on update restrict;
create index ix_advised_user_consultant_id on advised_user (consultant_id);

alter table profit add constraint fk_profit_user_id foreign key (user_id) references advised_user (id) on delete restrict on update restrict;
create index ix_profit_user_id on profit (user_id);

alter table spending add constraint fk_spending_user_id foreign key (user_id) references advised_user (id) on delete restrict on update restrict;
create index ix_spending_user_id on spending (user_id);


# --- !Downs

alter table advised_user drop foreign key fk_advised_user_consultant_id;
drop index ix_advised_user_consultant_id on advised_user;

alter table profit drop foreign key fk_profit_user_id;
drop index ix_profit_user_id on profit;

alter table spending drop foreign key fk_spending_user_id;
drop index ix_spending_user_id on spending;

drop table if exists advised_user;

drop table if exists consultant;

drop table if exists profit;

drop table if exists spending;

