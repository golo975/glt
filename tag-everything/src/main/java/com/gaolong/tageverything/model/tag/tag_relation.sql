
DROP TABLE IF EXISTS tag_relation;

CREATE TABLE tag_relation (
  id         BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  subject_id BIGINT(20) NOT NULL,
  object_id  BIGINT(20) NOT NULL,
  relation   INT        NOT NULL,
  create_time BIGINT(20)  NOT NULL
)