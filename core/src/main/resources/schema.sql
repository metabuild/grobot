create table TARGET_HOSTS (
  ID varchar(36) not null,
  NAME varchar(60) not null, 
  ADDRESS varchar(40) not null,
  REGISTERED DATE,
  unique UQ_TARGET_HOSTS_1 (ID), 
  unique UQ_TARGET_HOSTS_2 (NAME), 
  primary key (ID) 
); 

create table TARGET_GROUPS (
  ID varchar(36) not null,
  NAME varchar(60) not null,
  ACTIVE boolean default true,
  unique UQ_TARGET_GROUPS_1 (ID), 
  unique UQ_TARGET_GROUPS_2 (NAME), 
  primary key (ID) 
); 

create table TARGET_GROUP_MEMBERS (
  ID varchar(36) not null,
  TARGET_GROUP_ID varchar(60) not null, 
  TARGET_HOST_ID varchar(60) not null, 
  unique UQ_TARGET_GROUP_MEMBERS_1 (ID), 
  constraint FK_TARGET_GROUP_MEMBERS_1 foreign key (TARGET_GROUP_ID)
    references TARGET_GROUPS (ID) on delete cascade,
  constraint FK_TARGET_GROUP_MEMBERS_2 foreign key (TARGET_HOST_ID)
    references TARGET_HOSTS (ID) on delete cascade,
  primary key (ID) 
); 


create table TASKS (
  ID varchar(36) not null,
  NAME varchar(60) not null, 
  SCRIPT_NAME varchar(60), 
  unique UQ_TASKS_1 (ID), 
  unique UQ_TASKS_2 (NAME), 
  primary key (ID) 
);

create table TASK_EXECUTIONS (
  ID varchar(36) not null,
  TASK_ID varchar(36) not null,
  SCHEDULED_START_TIME DATE,
  ACTUAL_START_TIME DATE,
  END_TIME DATE,
  unique UQ_TASK_EXECUTIONS_1 (ID), 
  constraint FK_TASK_EXECUTIONS_1 foreign key (TASK_ID)
    references TASKS (ID) on delete cascade,
  primary key (ID) 
);

