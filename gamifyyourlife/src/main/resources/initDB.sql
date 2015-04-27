INSERT INTO COUNTRY (id, continent, name) VALUES (100, 'Europe', 'Germany')
INSERT INTO COUNTRY (id, continent, name) VALUES (101, 'Europe', 'Spain')
INSERT INTO COUNTRY (id, continent, name) VALUES (102, 'Asia', 'China')

INSERT INTO CITY (id, name, zip) VALUES (100, 'Frankfurt', '60000')
INSERT INTO CITY (id, name, zip) VALUES (101, 'Berlin', '50000')

INSERT INTO CATEGORY (id, description, name) VALUES (100, 'test', 'work')
INSERT INTO CATEGORY (id, description, name) VALUES (101, 'test', 'home')

INSERT INTO HERO (id, name) VALUES (100, 'Hero1')
INSERT INTO HERO (id, name) VALUES (101, 'Hero2')

INSERT INTO TODO_LIST (id) VALUES (100)

INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (100, 'Task1', 10, 100, 100)
INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (101, 'Task2', 5, 101, 100)

INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points) VALUES (100, 100, 'firstname1', 'lastname1', 'mail@mail.com', 'street1', 'test', 'test', 0)