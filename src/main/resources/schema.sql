CREATE TABLE user (
id IDENTITY PRIMARY KEY,
name VARCHAR (64)
);

CREATE TABLE account (
id IDENTITY PRIMARY KEY,
type VARCHAR (8),
user_id INT,

FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE card (
id IDENTITY PRIMARY KEY,
number VARCHAR (20),
payment_system VARCHAR (10),
card_holder VARCHAR (128),
expiry_date DATE,
account_id INT,
user_id INT,

FOREIGN KEY (account_id) REFERENCES account(id),
FOREIGN KEY (user_id) REFERENCES user(id)
);