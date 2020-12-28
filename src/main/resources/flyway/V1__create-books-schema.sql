DROP TABLE IF EXISTS `BOOK`;
CREATE TABLE IF NOT EXISTS `BOOK` (
  `id` VARCHAR(255) not null,
  `authorFirstName` VARCHAR(255),
  `authorLastName` VARCHAR(255)
);