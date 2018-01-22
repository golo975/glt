package com.gaolong.tageverything.model.tag;

/**
 * @author gaolong
 */
public class TagRelation {

    private int id;
    /**
     * 主语
     */
    private Tag subject;
    /**
     * 宾语
     */
    private Tag object;

    private Long createTime;


    private int relation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tag getSubject() {
        return subject;
    }

    public void setSubject(Tag subject) {
        this.subject = subject;
    }

    public Tag getObject() {
        return object;
    }

    public void setObject(Tag object) {
        this.object = object;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagRelation that = (TagRelation) o;

        if (id != that.id) return false;
        if (relation != that.relation) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (object != null ? !object.equals(that.object) : that.object != null) return false;
        return createTime != null ? createTime.equals(that.createTime) : that.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + relation;
        return result;
    }

    @Override
    public String toString() {
        return "TagRelation{" +
                "id=" + id +
                ", subject=" + subject +
                ", object=" + object +
                ", createTime=" + createTime +
                ", relation=" + relation +
                '}';
    }
}
