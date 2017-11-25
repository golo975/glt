package com.gaolong.tageverything.dao.tag;

import com.alibaba.fastjson.JSON;
import com.gaolong.tageverything.bean.DO.Tag;
import com.gaolong.tageverything.dao.MongoUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl implements TagDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public void save(Tag tag) {
//        Document document = Document.parse(JSON.toJSONString(tag));
//        MongoUtils.saveDocument(Tag.TABLE_NAME, document);

        jdbcTemplate.update("insert into tag (name) VALUES (?)", tag.getName());
    }

    @Override
    public Iterable<Tag> findAll() {
        return MongoUtils.findAll(Tag.TABLE_NAME, Tag.class);
    }
}
