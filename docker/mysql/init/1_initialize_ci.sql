CREATE DATABASE IF NOT EXISTS spa_cucumber_db;
USE spa_cucumber_db;

CREATE TABLE IF NOT EXISTS users(
  id           INT(10),
  last_name     VARCHAR(128),
  first_name     VARCHAR(128),
  gender   CHAR(1),
  phone_number  VARCHAR(128),
  email    VARCHAR(128),
  birth_date DATETIME,
  password VARCHAR(128)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
