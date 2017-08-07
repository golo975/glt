package com.gaolong.tageverything.service.tag;

import com.gaolong.tageverything.dao.tag.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

}