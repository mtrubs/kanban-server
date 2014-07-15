DROP DATABASE IF EXISTS ${database.name};
CREATE DATABASE ${database.name};
USE ${database.name};

CREATE TABLE Story (
  id          INT           NOT NULL AUTO_INCREMENT,
  title       VARCHAR(128)  NOT NULL,
  description VARCHAR(1024) NOT NULL,
  points      INT           NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE =InnoDB
  DEFAULT CHARACTER SET utf8;
