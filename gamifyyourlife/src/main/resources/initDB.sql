INSERT INTO COUNTRY (id, continent, name) VALUES (1, 'Europe', 'Germany')
INSERT INTO COUNTRY (id, continent, name) VALUES (2, 'Europe', 'Spain')
INSERT INTO COUNTRY (id, continent, name) VALUES (3, 'Asia', 'China')

INSERT INTO CITY (id, name, zip) VALUES (1, 'Frankfurt', '60000')
INSERT INTO CITY (id, name, zip) VALUES (2, 'Berlin', '50000')

INSERT INTO CATEGORY (id, description, name) VALUES (1, 'test', 'work')
INSERT INTO CATEGORY (id, description, name) VALUES (2, 'test', 'home')

INSERT INTO HERO (id, name) VALUES (1, 'Hero1')
INSERT INTO HERO (id, name) VALUES (2, 'Hero2')

INSERT INTO TODO_LIST (id) VALUES (100)

INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (1, 'Task1', 10, 1, 100)
INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (2, 'Task2', 5, 2, 100)

INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points) VALUES (100, 100, 'firstname1', 'lastname1', 'mail@mail.com', 'street1', 'test', 'test', 0)