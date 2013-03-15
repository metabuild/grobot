create table BOTS (
  ID varchar(36) not null,
  NAME varchar(60) not null, 
  ADDRESS varchar(40) not null,
  REGISTERED DATE,
  unique UQ_BOTS_1 (ID), 
  unique UQ_BOTS_2 (NAME), 
  primary key (ID) 
); 

create table BOT_GROUPS (
  ID varchar(36) not null,
  NAME varchar(60) not null,
  ACTIVE boolean default true,
  unique UQ_BOT_GROUPS_1 (ID), 
  unique UQ_BOT_GROUPS_2 (NAME), 
  primary key (ID) 
); 

create table BOT_GROUP_MEMBERS (
  ID varchar(36) not null,
  BOT_GROUP_ID varchar(60) not null, 
  BOT_ID varchar(60) not null, 
  unique UQ_BOT_GROUP_MEMBERS_1 (ID), 
  constraint FK_BOT_GROUP_MEMBERS_1 foreign key (BOT_GROUP_ID)
    references BOT_GROUPS (ID) on delete cascade,
  constraint FK_BOT_GROUP_MEMBERS_2 foreign key (BOT_ID)
    references BOTS (ID) on delete cascade,
  primary key (ID) 
); 


create table TASKS (
  ID varchar(36) not null,
  NAME varchar(60) not null, 
  SCRIPT_NAME varchar(60), 
  ARGUMENTS varchar(120), 
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

