# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table advised_user (
  id                            bigint not null,
  name                          varchar(255),
  id_advised_user               integer,
  consultant_id                 bigint,
  constraint pk_advised_user primary key (id)
);
create sequence advised_user_seq;

create table consultant (
  id                            bigint not null,
  name                          varchar(255),
  id_consultant                 integer,
  constraint pk_consultant primary key (id)
);
create sequence consultant_seq;

create table spending (
  id                            bigint not null,
  quantity                      float,
  description                   varchar(255),
  e_date                        timestamp,
  id_spending                   integer,
  user_id                       bigint,
  constraint pk_spending primary key (id)
);
create sequence spending_seq;

alter table advised_user add constraint fk_advised_user_consultant_id foreign key (consultant_id) references consultant (id) on delete restrict on update restrict;
create index ix_advised_user_consultant_id on advised_user (consultant_id);

alter table spending add constraint fk_spending_user_id foreign key (user_id) references advised_user (id) on delete restrict on update restrict;
create index ix_spending_user_id on spending (user_id);


# --- !Downs

alter table advised_user drop constraint if exists fk_advised_user_consultant_id;
drop index if exists ix_advised_user_consultant_id;

alter table spending drop constraint if exists fk_spending_user_id;
drop index if exists ix_spending_user_id;

drop table if exists advised_user;
drop sequence if exists advised_user_seq;

drop table if exists consultant;
drop sequence if exists consultant_seq;

drop table if exists spending;
drop sequence if exists spending_seq;

