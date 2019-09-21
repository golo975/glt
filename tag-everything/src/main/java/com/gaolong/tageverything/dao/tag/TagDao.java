package com.gaolong.tageverything.dao.tag;

import com.gaolong.tageverything.model.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

/**
 * @author gaolong
 */
@Repository
public class TagDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Long save(Tag tag) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO tag (name, create_time) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, tag.getName());
            preparedStatement.setLong(2, tag.getCreateTime());
            return preparedStatement;
        }, generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }


    public Tag getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tag WHERE id = ? ", mapper, id);
    }

    private RowMapper<Tag> mapper = (resultSet, i) -> {
        Tag tag = new Tag();
        tag.setId(resultSet.getLong("id"));
        tag.setName(resultSet.getString("name"));
        tag.setCreateTime(resultSet.getLong("create_time"));
        return tag;
    };
}
