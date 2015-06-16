# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table pessoa (
  id                        bigint not null,
  telefone                  varchar(255),
  data                      timestamp,
  constraint pk_pessoa primary key (id))
;

create sequence pessoa_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists pessoa;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists pessoa_seq;

