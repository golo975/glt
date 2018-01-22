package com.gaolong.tageverything.service.tag;

import com.gaolong.tageverything.constants.TagRelationEnum;
import com.gaolong.tageverything.model.tag.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TagRelationServiceTest {

    @Autowired
    TagRelationService tagRelationService;

    @Autowired
    TagService tagService;

    @Test
    public void saveTest() {
        Tag subject = tagService.getById(1L);
        Tag object = tagService.getById(2L);
        tagRelationService.save(TagRelationEnum.father, subject, object);

        tagRelationService.save(TagRelationEnum.father, subject, "son2");
    }
}
