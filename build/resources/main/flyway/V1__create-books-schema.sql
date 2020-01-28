DROP TABLE IF EXISTS `BOOK`;
CREATE TABLE IF NOT EXISTS `BOOK` (
  `hash` VARCHAR(255) not null,
  `name` VARCHAR(255) not null,
  `author` VARCHAR(255),
);