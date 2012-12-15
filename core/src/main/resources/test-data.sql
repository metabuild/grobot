insert into TARGET_HOSTS (ID, NAME, ADDRESS) values 
    ('a85765e0-7e90-47b9-aea3-7da16117d274','jburbridge-dev1', 'jburbridge-dev1.home.johnburbridge.net'),
    ('9741afbb-a1be-47e0-9aa9-8a7f3702ffea','fake-host1', 'fake-host1.dummy.domain'),
    ('686ffd94-cbf7-4767-bf77-9d529b0087e7','fake-host2', 'fake-host2.dummy.domain'),
    ('a56fde5f-0033-4050-8964-2b8d7470ee28','fake-host3', 'fake-host3.dummy.domain'),
    ('952ed06c-0283-4866-b8e6-d16ca6f57312','fake-host4', 'fake-host4.dummy.domain');

insert into TARGET_GROUPS (ID, NAME) values 
    ('225c172b-950f-4c24-9ee7-4d3b4ea1cf55','fake-group1',),
    ('1848d4b8-93b9-4970-be41-b366517af44b','fake-group2',);

select * from TARGET_HOSTS;
