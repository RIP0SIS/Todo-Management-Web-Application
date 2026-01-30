INSERT INTO todo (id, username, description, target_date, done) VALUES
(1001,'Aryan','Learn Java', CURRENT_DATE() ,false),
(1002,'Aryan','Learn Spring', CURRENT_DATE() ,false),
(1003,'Aryan','Learn Hibernate', CURRENT_DATE() ,false),
(1004,'Rip','Learn Hibernate', CURRENT_DATE() ,false),
(1005,'Rip','Learn React', CURRENT_DATE() ,false);

--Auto Increment after max id
ALTER TABLE todo ALTER COLUMN id RESTART WITH (
  SELECT MAX(id) + 1 FROM todo
);



