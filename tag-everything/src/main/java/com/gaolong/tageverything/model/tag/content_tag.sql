DROP TABLE IF EXISTS content_tag;
CREATE TABLE content_tag (
  content_id BIGINT(20) NOT NULL,
  tag_id     BIGINT(20) NOT NULL,
  PRIMARY KEY (content_id, tag_id)
);

