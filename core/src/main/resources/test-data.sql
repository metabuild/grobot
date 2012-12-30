insert into TARGET_HOSTS (ID, NAME, ADDRESS) values 
    ('a85765e0-7e90-47b9-aea3-7da16117d274','jburbridge-dev1', 'jburbridge-dev1.home.johnburbridge.net'),
    ('9741afbb-a1be-47e0-9aa9-8a7f3702ffea','fake-host1', 'fake-host1.dummy.domain'),
    ('686ffd94-cbf7-4767-bf77-9d529b0087e7','fake-host2', 'fake-host2.dummy.domain'),
    ('a56fde5f-0033-4050-8964-2b8d7470ee28','fake-host3', 'fake-host3.dummy.domain'),
    ('952ed06c-0283-4866-b8e6-d16ca6f57312','fake-host4', 'fake-host4.dummy.domain'),
    ('78bb9ccc-ca3d-4e9d-90b6-931c854ef7ef','fake-host5', 'fake-host5.dummy.domain'),
    ('b3539be2-bf93-4353-8bf4-8ee57c1e9161','fake-host6', 'fake-host6.dummy.domain'),
    ('515728ad-669b-4f37-80b8-54264201786d','fake-host7', 'fake-host7.dummy.domain'),
    ('2404cbd8-6a18-4170-ad99-2f416f52b06b','fake-host8', 'fake-host8.dummy.domain'),
    ('54ec2667-ef87-4b35-8fc0-0c31e9b8a1d6','fake-host9', 'fake-host9.dummy.domain');

insert into TARGET_GROUPS (ID, NAME, ACTIVE) values 
    ('225c172b-950f-4c24-9ee7-4d3b4ea1cf55','fake-group1', true),
    ('1848d4b8-93b9-4970-be41-b366517af44b','fake-group2', true),
    ('f2c1fafa-af63-499b-8bdb-d7a0df26837f','fake-group3', false);

insert into TARGET_GROUP_MEMBERS (ID, TARGET_GROUP_ID, TARGET_HOST_ID) values 
    ('c47946af-7482-4b4d-85b3-5f5424de3b13', '225c172b-950f-4c24-9ee7-4d3b4ea1cf55', 'a85765e0-7e90-47b9-aea3-7da16117d274'),
    ('b64399cd-a05c-4b88-99da-b18c9b16849c', '225c172b-950f-4c24-9ee7-4d3b4ea1cf55', '9741afbb-a1be-47e0-9aa9-8a7f3702ffea'),
    ('62f51b8d-4ca3-4dbc-86ca-e2f8ede55cc8', '225c172b-950f-4c24-9ee7-4d3b4ea1cf55', '686ffd94-cbf7-4767-bf77-9d529b0087e7'),
    ('44566de3-b313-4e68-a868-437858d82aec', '1848d4b8-93b9-4970-be41-b366517af44b', 'a56fde5f-0033-4050-8964-2b8d7470ee28'),
    ('f311841c-4af6-4c4c-8e1d-8dd5ca84d7e7', '1848d4b8-93b9-4970-be41-b366517af44b', '952ed06c-0283-4866-b8e6-d16ca6f57312');

insert into TASKS (ID, NAME, SCRIPT_NAME, ARGUMENTS) values
    ('d1f406ac-a9d2-40cf-a9b3-64e725f5bb36', 'fake-script1', 'fake-script-name1', 'foo bar'),
    ('88f0d086-e256-4c01-b9d5-d529480e7217', 'fake-script2', 'fake-script-name2', 'foo bar baz'),
    ('8314a775-50fc-4ec5-bc2c-2df0531a0f5c', 'fake-script3', 'fake-script-name3', 'bar baz'),
    ('1e95d712-b9b2-4e52-b598-b67565ff8182', 'fake-script4', 'fake-script-name4', 'foo baz'),
    ('56d66369-4b5f-4b31-bd71-e3f826d6a46c', 'fake-script5', 'fake-script-name5', null);

insert into TASK_EXECUTIONS (ID, TASK_ID) values
    ('9521a922-cc00-4126-89ce-818ca76d3eb7', 'd1f406ac-a9d2-40cf-a9b3-64e725f5bb36'),
    ('24a9587a-dbe9-452f-bc2a-3fa7e5e7f0f8', 'd1f406ac-a9d2-40cf-a9b3-64e725f5bb36'),
    ('7510156b-a3ec-453d-97f8-4b3fae1a38e8', 'd1f406ac-a9d2-40cf-a9b3-64e725f5bb36'),
    ('96684384-f684-4bfc-9d88-3ccb99daf491', '88f0d086-e256-4c01-b9d5-d529480e7217'),
    ('a5f4d3f2-a353-46a5-a908-f493ec399f0d', '88f0d086-e256-4c01-b9d5-d529480e7217');
    
select * from TARGET_HOSTS;

