--@block

CREATE TABLE Users(
    id INT PRIMARY KEY AUTO_INCREMENT, 
    -- primary key = must be unique
    email VARCHAR(255) NOT NULL UNIQUE, 
    bio TEXT,
    country VARCHAR(2)
);

--@block
INSERT INTO Users(email, bio, country)
VALUES(
    'hell@world.com',
    'TEST',
    'ID'
);

INSERT INTO Users(email, bio, country)
VALUES
    ('bye@world.com', 'foo', 'MX'),
    ('hello@guys.com', 'bar', 'US');


--@block READ FROM TABLE (but slow tho)
-- SELECT * FROM Users; 
SELECT id, email, country FROM Users 

WHERE country = 'ID'
-- OR id > 1
AND email LIKE 'hello%'

ORDER BY id DESC
LIMIT 2;

--@block INDEX
CREATE INDEX email_index ON Users(email);

--@block Relationship
CREATE TABLE Rooms(
    id INT AUTO_INCREMENT,
    street VARCHAR(255),
    owner_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES Users(id)
);
-- id is a primary key, owner id is a foreign key

--@block
INSERT INTO Rooms(owner_id, street) 
VALUES
    (1, 'street 1'),
    (1, 'street 2'),
    (1, 'street 3');

    --@block
    SELECT * FROM Users;
    SELECT * FROM Rooms;