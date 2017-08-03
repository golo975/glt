package com.gaolong.tageverything.model.tag;

public class TagRalation {

    private int id;
    private Tag subject;//主语
    private Tag predicate;//谓语
    private int relation;
    public static final int contain = 1;

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

    public Tag getPredicate() {
        return predicate;
    }

    public void setPredicate(Tag predicate) {
        this.predicate = predicate;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return subject.getName() + ":" + relation + ":" + predicate.getName();
    }
}
