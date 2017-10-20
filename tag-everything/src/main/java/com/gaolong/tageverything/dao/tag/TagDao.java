package com.gaolong.tageverything.dao.tag;

import com.gaolong.tageverything.bean.DO.Tag;

public interface TagDao {

    void save(Tag tag);

    Iterable<Tag> findAll();
}
