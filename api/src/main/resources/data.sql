DROP TABLE IF EXISTS items;

CREATE TABLE items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
);

INSERT INTO items (name) VALUES
  ('apples'),
  ('toothpaste'),
  ('bread')
