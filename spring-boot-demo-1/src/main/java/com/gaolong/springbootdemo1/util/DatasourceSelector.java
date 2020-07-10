package com.gaolong.springbootdemo1.util;

import com.gaolong.springbootdemo1.configuration.DatasourceConfig;

public class DatasourceSelector {
    private static final ThreadLocal<DatasourceConfig.DatasourceEnum> contextHolder = new ThreadLocal<>();

    public static void setCurrentDb(DatasourceConfig.DatasourceEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DatasourceConfig.DatasourceEnum getCurrentDb() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
