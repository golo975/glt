package com.glt.sdj.model.VO;

import com.glt.sdj.model.DO.Tag;

import java.util.List;

/**
 * Created by Administrator on 2017/12/24.
 */
public class TagPageVO {

    private int page;
    private int size;
    private long total;
    private List<Tag> content;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Tag> getContent() {
        return content;
    }

    public void setContent(List<Tag> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TagPageVO{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", content=" + content +
                '}';
    }
}
