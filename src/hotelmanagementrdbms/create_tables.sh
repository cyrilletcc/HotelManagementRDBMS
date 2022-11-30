#!/usr/bin/env bash

source local_config.sh
#export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib
sqlplus64 $oracle_login  <<EOF

--------------------------------------------------------
-- department
-- emp = 1, guest = 2, room = 3, supplier = 4, facilities = 5, recreation = 6, diningroom = 7

CREATE TABLE department (
    current_supplies    VARCHAR2(15),  
    dept_name           VARCHAR2(35), 
    dept_id             NUMBER PRIMARY KEY 
);

--------------------------------------------------------
-- employee
CREATE TABLE employee (
    address             VARCHAR2(35),
    dob                 DATE, 
    emp_type            VARCHAR(35) NOT NULL,  
    emp_f_name          VARCHAR2(35) NOT NULL,
    emp_l_name          VARCHAR2(35) NOT NULL,
    phone_number        NUMBER CHECK (phone_number > 0),
    team                CHAR(4) NOT NULL,
    emp_role            VARCHAR2(35),
    emp_id              NUMBER PRIMARY KEY,
    dept_id             REFERENCES department(dept_id)
);

--------------------------------------------------------
-- guest
CREATE TABLE guest (
    guest_f_name         VARCHAR2(35) NOT NULL,
    guest_l_name         VARCHAR2(35) NOT NULL,
    guest_id             NUMBER PRIMARY KEY,
    dept_id              REFERENCES department(dept_id)
);

--------------------------------------------------------
-- room
CREATE TABLE room ( 
    book_in             DATE,
    booking_date        DATE,
    booking_ref         NUMBER NOT NULL,
    check_out           DATE,
    room_id             NUMBER PRIMARY KEY,
    dept_id             NUMBER REFERENCES department(dept_id),
    guest_id            NUMBER REFERENCES guest(guest_id)
);

--------------------------------------------------------
-- occupies
CREATE TABLE occupies (
    end_date            DATE,
    start_date          DATE,
    guest_id            NUMBER REFERENCES guest(guest_id),
    room_id             NUMBER REFERENCES room(room_id)
);

--------------------------------------------------------
-- supplier
CREATE TABLE supplier(
    name_of_good        VARCHAR2(20),
    sup_name            VARCHAR2(20)         NOT NULL,
    sup_address         VARCHAR2(20)         NOT NULL,
    sup_phone_number    VARCHAR2(20),
    purchase_price      FLOAT,
    order_num           NUMBER, 
    supplier_id         NUMBER PRIMARY KEY,
    dept_id             NUMBER REFERENCES department(dept_id)
);

--------------------------------------------------------
-- facilities
CREATE TABLE facilities (
    fac_capacity       NUMBER,
    fac_type           VARCHAR2(15),
    fac_id             NUMBER PRIMARY KEY,
    dept_id            NUMBER REFERENCES department(dept_id)
);

--------------------------------------------------------
-- recreation
CREATE TABLE recreation(
    amenity_capacity   NUMBER,
    daily_guests       NUMBER,
    last_serviced      DATE,
    rec_time           DATE,
    rec_type           VARCHAR2(15),
    rec_key            NUMBER PRIMARY KEY,
    dept_id            NUMBER REFERENCES department(dept_id)
);

--------------------------------------------------------
-- diningroom
CREATE TABLE diningroom(
    amenity_capacity   NUMBER,
    daily_guests       NUMBER,
    last_serviced      DATE,
    food_time          DATE,
    din_room_id        NUMBER PRIMARY KEY,
    dept_id            NUMBER REFERENCES department(dept_id)
);

--------------------------------------------------------
-- menu
CREATE TABLE menu(
    menu_constraints   VARCHAR2(25), 
    food_price         FLOAT,
    spicy              CHAR(10),
    food_type          VARCHAR2(30),
    menu_id            REFERENCES diningroom(din_room_id) ON DELETE CASCADE
);

---------------------------------------------------------
-- views

-- View 1
CREATE VIEW employeeview AS
    SELECT
        emp_type      "Type",
        emp_role      "Role",
        team          "Team",
        emp_f_name || ' ' || emp_l_name "Full Name"
    FROM
        employee;

-- View 2
CREATE VIEW supplierview AS
    SELECT
        purchase_price
    FROM
        supplier;

-- View 3
CREATE VIEW coffeemenuview AS
    SELECT
        *
    FROM
        menu
    WHERE
        spicy = 'Sweet';


EOF
