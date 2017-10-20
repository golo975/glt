package com.gaolong.tageverything.dao.tag;

import com.alibaba.fastjson.JSON;
import com.gaolong.tageverything.bean.DO.Tag;
import com.gaolong.tageverything.dao.MongoUtils;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl implements TagDao {

    @Override
    public void save(Tag tag) {
        Document document = Document.parse(JSON.toJSONString(tag));
        MongoUtils.saveDocument(Tag.TABLE_NAME, document);
    }

    @Override
    public Iterable<Tag> findAll() {
        return MongoUtils.findAll(Tag.TABLE_NAME, Tag.class);
    }
}
