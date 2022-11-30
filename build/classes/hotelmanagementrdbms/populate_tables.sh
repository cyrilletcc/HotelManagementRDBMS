#!/usr/bin/env bash

source local_config.sh
#export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib
sqlplus64 $oracle_login  <<EOF


--------------------------------------------------------
 -- Populating the tables
--------------------------------------------------------
-- Department Table
-- emp = 1, guest = 2, room = 3, supplier = 4, facilities = 5, recreation = 6, diningroom = 7
INSERT INTO department VALUES ('No', 'Human Resource', 1);
INSERT INTO department VALUES ('No', 'Front Desk', 2);
INSERT INTO department VALUES ('Yes', 'Room Service', 3);
INSERT INTO department VALUES ('No', 'Purchasing', 4);
INSERT INTO department VALUES ('Yes', 'Maintenance', 5);
INSERT INTO department VALUES ('Yes', 'Amusement', 6);
INSERT INTO department VALUES ('Yes', 'Restauration', 7);

--------------------------------------------------------
--Guest Table
INSERT INTO guest VALUES ('JOHN', 'SMITH', 1, 2);
INSERT INTO guest VALUES ('JIM', 'BARG', 2, 2);
INSERT INTO guest VALUES ('BOB', 'LOLLY', 3, 2);
INSERT INTO guest VALUES ('JILL', 'MAJED', 4, 2);
INSERT INTO guest VALUES ('DANIEL', 'SPRINGER', 5, 2);
INSERT INTO guest VALUES ('ALICE', 'AMLEH', 6, 2);
INSERT INTO guest VALUES ('KRISTA', 'KIM', 7, 2);

--------------------------------------------------------
--Room Table


INSERT INTO room VALUES (TO_DATE('2022/10/01','yy/mm/dd'), TO_DATE('2022/09/01','yy/mm/dd'), 1000, TO_DATE('2022/10/31','yy/mm/dd'), 99, 3, 1);
INSERT INTO room VALUES (TO_DATE('2022/10/01','yy/mm/dd'), TO_DATE('2022/09/01','yy/mm/dd'), 1001, TO_DATE('2022/10/31','yy/mm/dd'), 100, 3, 2);
INSERT INTO room VALUES (TO_DATE('2022/10/05','yy/mm/dd'), TO_DATE('2022/08/01','yy/mm/dd'), 1002, TO_DATE('2022/11/30','yy/mm/dd'), 101, 3, 3);
INSERT INTO room VALUES (TO_DATE('2022/12/01','yy/mm/dd'), TO_DATE('2022/01/01','yy/mm/dd'), 1003, TO_DATE('2022/12/04','yy/mm/dd'), 102, 3, 4);


--------------------------------------------------------
--Employee Table


INSERT INTO employee VALUES('54 Software Drive', TO_DATE('2001-11-03','yy/mm/dd'),'Full-Time','Gaurav','Divecha',6572832481,'A','Supervisor',773001,1);
INSERT INTO employee VALUES('7 Hardware Drive',TO_DATE('2002-01-30','yy/mm/dd'),'Full-Time','Jacky','Sanchez',2612352412,'A','Hiring Manager',773002,1);
INSERT INTO employee VALUES('70 Hare Crescent',TO_DATE('2000-11-03','yy/mm/dd'),'Full-Time','Jim','Karl',2612352412,'A','Facilities Manager',773004,5);
INSERT INTO employee VALUES('7 Hardware Drive',TO_DATE('1997-04-17','yy/mm/dd'),'Full-Time','Joshua','Eygenram',2612352412,'A','Customer Experience Manager',773005,2);
INSERT INTO employee VALUES('42 Hybrid Crescent',TO_DATE('1996-07-29','yy/mm/dd'),'Full-Time','Cyrille','Teupe',6572891481,'B','Supervisor',773003,1);
INSERT INTO employee VALUES('78 Killmonger Road',TO_DATE('1999-10-23','yy/mm/dd'),'Full-Time','Chris','Hemsworth',6752457129,'B','Cook Manager',774009,7);
INSERT INTO employee VALUES('78 Illinois Crescent',TO_DATE('1986-01-03','yy/mm/dd'),'Part-Time','Chadwik','Boseman',8972453687,'A','Receptionist',781001,2);
INSERT INTO employee VALUES('808, 51 Moore Crescent',TO_DATE('1997-12-03','yy/mm/dd'),'Full-Time','Cristiano','Ronaldo',9826738916,'A','Guest Service Agent',768901,2);
INSERT INTO employee VALUES('11 Crisscross Drive',TO_DATE('1990-10-02','yy/mm/dd'),'Part-Time','Lionel','Messi',7283546190,'B','Room Service Agent',773206,3);
INSERT INTO employee VALUES('67 Brazuka Hill Drive',TO_DATE('1991-11-02','yy/mm/dd'),'Full-Time','Naruto','Uzumaki',7862453678,'A','Supply Tracker',672206,4);
INSERT INTO employee VALUES('98 Highview Drive',TO_DATE('1996-09-12','yy/mm/dd'),'Full-Time','Gon','Freecs',6725168978,'A','Facility Tracker',772246,5);
INSERT INTO employee VALUES('76 Greed Island',TO_DATE('1986-1-12','yy/mm/dd'),'Full-Time','Ryan','Garcia',6724356769,'B','Activities Coordinator',793246,6);
INSERT INTO employee VALUES('56 O2 Arena',TO_DATE('1986-7-06','yy/mm/dd'),'Part-Time','Jeff','Bezos',1672456731,'A','Chef',778646,7);

--------------------------------------------------------
--Facilities Table

INSERT INTO facilities VALUES(100, 'parking lot', 1, 5);
INSERT INTO facilities VALUES(25, 'garden', 2, 5);
INSERT INTO facilities VALUES(15, 'foyer', 3, 5);
INSERT INTO facilities VALUES(175, 'boardwalk', 4, 5);
INSERT INTO facilities VALUES(110, 'coat room', 5, 5);

--------------------------------------------------------
--Occupies Table


INSERT INTO occupies VALUES(TO_DATE('2022/10/01','yy/mm/dd'), TO_DATE('2022/10/31','yy/mm/dd'), 1 , 100);
INSERT INTO occupies VALUES(TO_DATE('2022/10/01','yy/mm/dd'), TO_DATE('2022/09/01','yy/mm/dd'), 2 , 100);
INSERT INTO occupies VALUES(TO_DATE('2022/10/05','yy/mm/dd'), TO_DATE('2022/08/01','yy/mm/dd'), 3 , 101);
INSERT INTO occupies VALUES(TO_DATE('2022/12/01','yy/mm/dd'), TO_DATE('2022/12/01','yy/mm/dd'), 4 , 102);
INSERT INTO occupies VALUES(TO_DATE('2022/10/04','yy/mm/dd'), TO_DATE('2022/10/07','yy/mm/dd'), 5 , 100);

--------------------------------------------------------
--Recreation Table



INSERT INTO recreation VALUES(5, 32, TO_DATE('2022/10/01','yy/mm/dd'),  TO_DATE('10:00:00','hh24:mi:ss'), 'hottub', 1, 6);
INSERT INTO recreation VALUES(150, 44, TO_DATE('2022/11/02','yy/mm/dd'),  TO_DATE('7:00:00','hh24:mi:ss'), 'pool', 2, 6);
INSERT INTO recreation VALUES(15, 10, TO_DATE('1999/02/01','yy/mm/dd'),  TO_DATE('06:00:00','hh24:mi:ss'), 'gym', 3, 6);

--------------------------------------------------------
-- Dining Room


INSERT INTO diningroom VALUES(40, 120, TO_DATE('2022/10/02','yy/mm/dd'),TO_DATE('2022/10/03 08:00:00','YYYY/MM/DD HH24:MI:SS'), 1, 7);
INSERT INTO diningroom VALUES(5, 22, TO_DATE('2022/11/22','yy/mm/dd'),TO_DATE('2022/10/03 12:30:00','YYYY/MM/DD HH24:MI:SS'), 2, 7);
INSERT INTO diningroom VALUES(11, 35, TO_DATE('2022/10/02','yy/mm/dd'),TO_DATE('2022/10/03 15:30:00','YYYY/MM/DD HH24:MI:SS'), 3, 7);

--------------------------------------------------------
-- supplier


INSERT INTO supplier VALUES ('Cheese','Triple A Cheese', '120 Newkirk Road','0023044524',2.40,1,1,7);
INSERT INTO supplier VALUES ('Vegetable','High Farm', '98 Dufferin Street','1923044524',6.40,2,2,7);
INSERT INTO supplier VALUES ('Oil','Triple A Cheese', '120 Newkirk Road','0023044524',2.40,3,3,7);
INSERT INTO supplier VALUES ('Fish','Sea Food Company', '12 Midland Road','894522324',12.90,4,4,7);
INSERT INTO supplier VALUES ('Light bulbs','Star Electro', '1 Yonge Street','994322344',4.99,5,5,5);
INSERT INTO supplier VALUES ('Cleaning supplies','Meadover','2 Brisbane Road','133324524',3.55,6,6,3);
INSERT INTO supplier VALUES ('Television','Best Buy', '37 Highway 7','556644524',640.5,12,7,3);
INSERT INTO supplier VALUES ('Napkins','Staple', '120 Clark Avenue ','124254524',12.40,7,8,3);
INSERT INTO supplier VALUES ('Notepad','Staple', '120 Clark Avenue ','124254524',1.40,8,9,1);
INSERT INTO supplier VALUES ('Table set','Staple', '120 Clark Avenue ','124254524',24.76,7,10,3);

--------------------------------------------------------
-- Menu


INSERT INTO menu VALUES('Vegetarian',19.99,'Mild','Greek Salad with Cheese',1);
INSERT INTO menu VALUES('Meat',15.99,'Hot','Kung Pao Chicken',1);
INSERT INTO menu VALUES('Vegan',11.99,'Mild','Vegan Potato Salad',1);
INSERT INTO menu VALUES('Vegetarian',9.99,'Hot','Large Crusty Pizza',1);
INSERT INTO menu VALUES('Meat',18.99,'Mild','Party Buffet Composed Salad',1);
INSERT INTO menu VALUES('No Peanuts',3.99,'Sweet','Chocolate Filled Croissant',2);
INSERT INTO menu VALUES('No Peanuts',4.99,'Sour','Garlic Bread',2);
INSERT INTO menu VALUES('No Peanuts',8.99,'Sweet','Cream-filled Doughnut',2);
INSERT INTO menu VALUES('No Peanuts',7.99,'Sweet','Organic Almonds',2);
INSERT INTO menu VALUES('Sugarless',9.99,'Sour','Crackers',2);
INSERT INTO menu VALUES('Sugarless',4.99,'Mild','Hashbrown',2);
INSERT INTO menu VALUES('Sugarless',2.99,'Hot','Jalapeno Bagels',2);
INSERT INTO menu VALUES('Sugarless',4.99,'Sweet','Sugarless Cookies',2);
INSERT INTO menu VALUES('Decaf',1.99,'Sweet','Coffee',3);
INSERT INTO menu VALUES('Caffeine',2.99,'Sweet','Americano',3);
INSERT INTO menu VALUES('Caffeine',1.50,'Sweet','Cappacino',3);

----------------------


EOF
