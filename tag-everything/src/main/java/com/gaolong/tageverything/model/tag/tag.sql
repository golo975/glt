
DROP TABLE IF EXISTS tag;

CREATE TABLE tag (
  id         BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(50) NULL,
  create_time BIGINT(20)  NOT NULL
);