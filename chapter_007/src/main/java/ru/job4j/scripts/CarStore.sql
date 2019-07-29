CREATE TABLE Carcass (
    id SERIAL PRIMARY KEY,
    num VARCHAR(10),
    type VARCHAR (15)
);

CREATE TABLE ShiftGear (
    id SERIAL PRIMARY KEY,
    num VARCHAR(10),
    type VARCHAR(15),
    gears_amount int
);

CREATE TABLE  Engine (
    id SERIAL PRIMARY KEY,
    num VARCHAR(10),
    type VARCHAR(15),
    piston_amount int
);

CREATE TABLE Car (
    id SERIAL PRIMARY KEY,
    num VARCHAR(15),
    carcass INTEGER REFERENCES Carcass(id),
    shift_gear INTEGER REFERENCES ShiftGear(id),
    engine INTEGER REFERENCES Engine(id)
);

INSERT INTO Carcass (num, type) VALUES ('101', 'sedan');
INSERT INTO ShiftGear (num, type, gears_amount) VALUES ('201', 'automatic', 6);
INSERT INTO Engine (num, type, piston_amount) VALUES ('301', 'inline', 4);
INSERT INTO Car (num, carcass, shift_gear, engine) VALUES ('001', 1, 1, 1);
INSERT INTO Car (num) values ('002');

--only stuffed
SELECT * from Car c
INNER JOIN Carcass carc ON  c.carcass = carc.id
INNER JOIN ShiftGear shift ON c.shift_gear = shift.id
INNER JOIN Engine e ON c.engine = e.id;

--all car
SELECT * FROM Car c
LEFT OUTER JOIN Carcass carc ON  c.carcass = carc.id
LEFT OUTER JOIN ShiftGear shift ON c.shift_gear = shift.id
LEFT OUTER JOIN Engine e ON c.engine = e.id;


-- unused carcasses
SELECT * FROM Carcass carc
LEFT OUTER JOIN Car c ON c.carcass = carc.id
WHERE c.carcass IS null;

-- unused engines
SELECT * FROM Engine e
LEFT OUTER JOIN Car c ON c.engine = e.id
WHERE c.engine IS null;

-- unused shift gears
SELECT * FROM ShiftGear s
LEFT OUTER JOIN Car c ON c.shift_gear = s.id
WHERE c.shift_gear IS null;