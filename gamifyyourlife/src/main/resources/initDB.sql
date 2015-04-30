INSERT INTO COUNTRY (id, continent, name) VALUES (100, 'Europe', 'Germany')
INSERT INTO COUNTRY (id, continent, name) VALUES (101, 'Europe', 'Spain')
INSERT INTO COUNTRY (id, continent, name) VALUES (102, 'Asia', 'China')

INSERT INTO CITY (id, name, zip) VALUES (100, 'Frankfurt', '60000')
INSERT INTO CITY (id, name, zip) VALUES (101, 'Berlin', '50000')

INSERT INTO CATEGORY (id, description, name) VALUES (100, 'category for work', 'work')
INSERT INTO CATEGORY (id, description, name) VALUES (101, 'category for home', 'home')

INSERT INTO HERO (id, name) VALUES (100, 'Superman')
INSERT INTO HERO (id, name) VALUES (101, 'Batman')

INSERT INTO TODO_LIST (id) VALUES (100)

INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (100, 'Math Homework', 10, 100, 100)
INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (101, 'English Homework', 5, 101, 100)

INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points) VALUES (100, 100, 'Thomas', 'Roehl', 'mail@googlemail.com', 'leipzigerstr. 300', 'tro', 'test', 0)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points) VALUES (100, 100, 'Max', 'Mustermann', 'mail@googlemail.com', 'leipzigerstr. 300', 'max', 'test', 0)
