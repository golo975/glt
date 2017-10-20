package com.gaolong.tageverything.bean.DO;

/**
 * Created by Administrator on 2017/10/15.
 */
public class Tag {
    public static final String TABLE_NAME = "tag";
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}