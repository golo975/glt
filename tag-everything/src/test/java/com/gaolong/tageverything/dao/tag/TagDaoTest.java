package com.gaolong.tageverything.dao.tag;

import com.gaolong.tageverything.bean.DO.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application.xml",
        "classpath:application-mvc.xml"})
public class TagDaoTest {

    @Autowired
    private TagDao tagDao;

    @Test
    public void saveTest() {
        Tag tag = new Tag();
        tag.setName("gaolong");
        tagDao.save(tag);
    }
}
