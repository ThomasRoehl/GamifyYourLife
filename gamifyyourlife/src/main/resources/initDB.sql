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
INSERT INTO TODO_LIST (id) VALUES (101)
INSERT INTO TODO_LIST (id) VALUES (102)
INSERT INTO TODO_LIST (id) VALUES (103)
INSERT INTO TODO_LIST (id) VALUES (104)
INSERT INTO TODO_LIST (id) VALUES (105)
INSERT INTO TODO_LIST (id) VALUES (106)
INSERT INTO TODO_LIST (id) VALUES (107)
INSERT INTO TODO_LIST (id) VALUES (108)
INSERT INTO TODO_LIST (id) VALUES (109)
INSERT INTO TODO_LIST (id) VALUES (110)
INSERT INTO TODO_LIST (id) VALUES (111)
INSERT INTO TODO_LIST (id) VALUES (112)
INSERT INTO TODO_LIST (id) VALUES (113)
INSERT INTO TODO_LIST (id) VALUES (114)
INSERT INTO TODO_LIST (id) VALUES (115)
INSERT INTO TODO_LIST (id) VALUES (116)
INSERT INTO TODO_LIST (id) VALUES (117)
INSERT INTO TODO_LIST (id) VALUES (118)
INSERT INTO TODO_LIST (id) VALUES (119)
INSERT INTO TODO_LIST (id) VALUES (120)
INSERT INTO TODO_LIST (id) VALUES (121)

INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (100, 'Math Homework', 10, 100, 100)
INSERT INTO TASK (id, name, points, category_fk, todo_list_fk) VALUES (101, 'English Homework', 5, 101, 100)

INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (100, 100, 'Thomas', 'Roehl', 'mail@googlemail.com', 'leipzigerstr. 300', 'tro', 'test', 1, 100, 100, 100, 10)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (101, 101, 'Max', 'Mustermann', 'mail@googlemail.com', 'leipzigerstr. 300', 'max', 'test', 2, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (102, 102, 'Jan', 'Alix', 'mail@googlemail.com', 'leipzigerstr. 300', 'Jan', 'test', 3, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (103, 103, 'Collen', 'Maharaj', 'mail@googlemail.com', 'leipzigerstr. 300', 'Collen', 'test', 4, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (104, 104, 'Karyl', 'Partlow', 'mail@googlemail.com', 'leipzigerstr. 300', 'Karyl', 'test', 5, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (105, 105, 'Jonathan', 'Ma', 'mail@googlemail.com', 'leipzigerstr. 300', 'Jonathan', 'test', 6, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (106, 106, 'Reatha', 'Mullan', 'mail@googlemail.com', 'leipzigerstr. 300', 'Reatha', 'test', 7, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (107, 107, 'Chanell', 'Thompkins', 'mail@googlemail.com', 'leipzigerstr. 300', 'Chanell', 'test', 8, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (108, 108, 'Darwin', 'Repp', 'mail@googlemail.com', 'leipzigerstr. 300', 'Darwin', 'test', 9, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (109, 109, 'Shanita', 'Letsinger', 'mail@googlemail.com', 'leipzigerstr. 300', 'Shanita', 'test', 10, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (110, 110, 'Ping', 'Brundige', 'mail@googlemail.com', 'leipzigerstr. 300', 'Ping', 'test', 11, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (111, 111, 'Blanch', 'Demaria', 'mail@googlemail.com', 'leipzigerstr. 300', 'Blanch', 'test', 12, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (112, 112, 'Clemente', 'Pollan', 'mail@googlemail.com', 'leipzigerstr. 300', 'Clemente', 'test', 13, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (113, 113, 'Flo', 'Koeppel', 'mail@googlemail.com', 'leipzigerstr. 300', 'Flo', 'test', 14, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (114, 114, 'Dorotha', 'Mosely', 'mail@googlemail.com', 'leipzigerstr. 300', 'Dorotha', 'test', 15, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (115, 115, 'Jarrod', 'Heater', 'mail@googlemail.com', 'leipzigerstr. 300', 'Jarrod', 'test', 16, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (116, 116, 'Carmella', 'Busbee', 'mail@googlemail.com', 'leipzigerstr. 300', 'Carmella', 'test', 17, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (117, 117, 'Corina', 'Kimzey', 'mail@googlemail.com', 'leipzigerstr. 300', 'Corina', 'test', 18, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (118, 118, 'Lourdes', 'Dubuc', 'mail@googlemail.com', 'leipzigerstr. 300', 'Lourdes', 'test', 19, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (119, 119, 'Rolf', 'Hendren', 'mail@googlemail.com', 'leipzigerstr. 300', 'Rolf', 'test', 20, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (120, 120, 'Thi', 'Trail', 'mail@googlemail.com', 'leipzigerstr. 300', 'Thi', 'test', 21, 101, 101, 101, 8)
INSERT INTO USERPROFILE (id, todo_list_fk, firstname, lastname, mail, street1, username, password, points, country_fk, city_fk, hero_fk, hero_level) VALUES (121, 121, 'Verena', 'Donlan', 'mail@googlemail.com', 'leipzigerstr. 300', 'Verena', 'test', 22, 101, 101, 101, 8)

INSERT INTO ACHIEVEMENT (id, name, description, points_needed) VALUES (10, 'New Hero', 'create a new Hero', 5)
INSERT INTO ACHIEVEMENT (id, name, description, points_needed) VALUES (20, 'Beginner', 'start your new life', 10)
INSERT INTO ACHIEVEMENT (id, name, description, points_needed) VALUES (30, 'Young padawan', 'much to learn you still have', 20)
INSERT INTO ACHIEVEMENT (id, name, description, points_needed) VALUES (40, 'I want to be better', 'there is nothing you cant do', 30)
INSERT INTO ACHIEVEMENT (id, name, description, points_needed) VALUES (50, 'Fast learner', 'so fast ...', 40)
