insert into BOTS (ID, NAME, ADDRESS) values 
    ('a85765e0-7e90-47b9-aea3-7da16117d274','botName', 'botAddress');

insert into BOT_GROUPS (ID, NAME, DESCRIPTION, ACTIVE) values 
    ('225c172b-950f-4c24-9ee7-4d3b4ea1cf55','groupName', 'Group description', true);

insert into BOT_GROUP_MEMBERS (BOT_GROUP_ID, BOT_ID) values 
    ('225c172b-950f-4c24-9ee7-4d3b4ea1cf55', 'a85765e0-7e90-47b9-aea3-7da16117d274');

insert into TASKS (ID, NAME, SCRIPT_NAME, ARGUMENTS) values
    ('d1f406ac-a9d2-40cf-a9b3-64e725f5bb36', 'fake-script1', 'fake-script-name1', 'foo bar');

insert into TASK_EXECUTIONS (ID, TASK_ID) values
    ('9521a922-cc00-4126-89ce-818ca76d3eb7', 'd1f406ac-a9d2-40cf-a9b3-64e725f5bb36');

