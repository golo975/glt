package com.gaolong.tageverything.service.tag;

import com.gaolong.tageverything.dao.tag.TagDao;
import com.gaolong.tageverything.model.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gaolong
 */
@Service
public class TagService {

    private final TagDao tagDao;

    @Autowired
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public Long add(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setCreateTime(System.currentTimeMillis());
        return tagDao.save(tag);
    }

    public Tag getById(Long id) {
        return tagDao.getById(id);
    }


}