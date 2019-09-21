package com.glt.sdj.service;

import com.glt.sdj.dao.TagDao;
import com.glt.sdj.model.DO.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagDao tagDao;

    @Autowired
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public Page<Tag> findByPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return tagDao.findAll(pageable);
    }

    public Tag add(Tag tag) {
        return tagDao.save(tag);
    }

}
