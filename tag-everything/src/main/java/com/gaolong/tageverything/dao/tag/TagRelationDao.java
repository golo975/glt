package com.gaolong.tageverything.dao.tag;

import com.gaolong.tageverything.model.tag.TagRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author gaolong
 */
@Repository
public class TagRelationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagRelationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(TagRelation tagRalation) {
        PreparedStatementSetter pss = preparedStatement -> {
            preparedStatement.setLong(1, tagRalation.getSubject().getId());
            preparedStatement.setLong(2, tagRalation.getObject().getId());
            preparedStatement.setInt(3, tagRalation.getRelation());
            preparedStatement.setLong(4, tagRalation.getCreateTime());
        };
        jdbcTemplate.update("INSERT INTO tag_relation (subject_id, object_id, relation, create_time) VALUE (?,?,?,?)", pss);
    }


}
