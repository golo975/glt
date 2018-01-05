package com.glt.sdj.controller;

import com.glt.sdj.model.DO.Tag;
import com.glt.sdj.model.VO.TagPageVO;
import com.glt.sdj.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "page/{page}", method = RequestMethod.GET)
    public TagPageVO findByPage(@PathVariable Integer page,
                                @RequestParam(defaultValue = "10") Integer size) {
        logger.info("/page/{page}?size={size}", page, size);
        Page<Tag> byPage = tagService.findByPage(page, size);
        TagPageVO tagPageVO = new TagPageVO();
        tagPageVO.setTotal(byPage.getTotalElements());
        tagPageVO.setContent(byPage.getContent());
        tagPageVO.setPage(page);
        tagPageVO.setSize(size);
        logger.info("findByPage result : page={page}, size={size}, total={total}.",
                page, size, byPage.getTotalElements());
        return tagPageVO;
    }

    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Tag add(@RequestBody Tag tag) {
        Tag add = tagService.add(tag);
        logger.info("add new tag success : {tag}", tag);
        return add;
    }


}
