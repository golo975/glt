package com.gaolong.tageverything.dao.tag;

import com.gaolong.tageverything.constants.TagRelationEnum;
import com.gaolong.tageverything.model.tag.Tag;
import com.gaolong.tageverything.model.tag.TagRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TagRelationDaoTest {

    @Autowired
    TagRelationDao tagRelationDao;

    @Test
    public void saveTest() {
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("test");
        tag1.setCreateTime(System.currentTimeMillis());

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("test2");
        tag2.setCreateTime(System.currentTimeMillis());

        TagRelation tagRelation = new TagRelation();
        tagRelation.setSubject(tag1);
        tagRelation.setObject(tag2);
        tagRelation.setRelation(TagRelationEnum.father.code);
        tagRelation.setCreateTime(System.currentTimeMillis());

        tagRelationDao.save(tagRelation);
    }
}
