package com.gaolong.tageverything.constants;

public enum TagRelationEnum {
    father(1),
    son(-1);

    public int code;

    TagRelationEnum(int code) {
        this.code = code;
    }
}
