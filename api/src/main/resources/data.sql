DROP TABLE IF EXISTS grocery_item;

CREATE TABLE grocery_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
);

INSERT INTO grocery_item (name) VALUES
  ('apples'),
  ('toothpaste'),
  ('bread')
