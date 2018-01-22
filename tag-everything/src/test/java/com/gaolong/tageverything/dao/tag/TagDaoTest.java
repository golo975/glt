package com.gaolong.tageverything.dao.tag;

import com.gaolong.tageverything.model.tag.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TagDaoTest {

    @Autowired
    private TagDao tagDao;


    @Test
    public void addTest() {
        Tag tag = new Tag();
        tag.setName("test");
        tag.setCreateTime(System.currentTimeMillis());
        Long id = tagDao.save(tag);
        System.out.println(id);
    }

    @Test
    public void getByIdTest() {
        Tag tag = tagDao.getById(2L);
        System.out.println(tag);
    }
}
