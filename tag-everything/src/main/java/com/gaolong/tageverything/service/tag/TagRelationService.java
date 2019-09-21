package com.gaolong.tageverything.service.tag;

import com.gaolong.tageverything.constants.TagRelationEnum;
import com.gaolong.tageverything.dao.tag.TagDao;
import com.gaolong.tageverything.dao.tag.TagRelationDao;
import com.gaolong.tageverything.model.tag.Tag;
import com.gaolong.tageverything.model.tag.TagRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gaolong
 */
@Service
public class TagRelationService {
    private final TagRelationDao tagRelationDao;
    private final TagService tagService;

    @Autowired
    public TagRelationService(TagRelationDao tagRelationDao, TagService tagService) {
        this.tagRelationDao = tagRelationDao;
        this.tagService = tagService;
    }

    public void save(TagRelationEnum relation, Tag subject, Tag object) {
        TagRelation tagRelation = new TagRelation();
        tagRelation.setSubject(subject);
        tagRelation.setObject(object);
        tagRelation.setRelation(relation.code);
        tagRelation.setCreateTime(System.currentTimeMillis());
        tagRelationDao.save(tagRelation);
    }

    public void save(TagRelationEnum relation, Tag subject, String objectTagName) {
        Long id = tagService.add(objectTagName);
        Tag object = tagService.getById(id);

        TagRelation tagRelation = new TagRelation();
        tagRelation.setSubject(subject);
        tagRelation.setObject(object);
        tagRelation.setRelation(relation.code);
        tagRelation.setCreateTime(System.currentTimeMillis());
        tagRelationDao.save(tagRelation);

    }

}
