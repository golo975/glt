package com.glt.dubbo;

/**
 * Created by Administrator on 2017/10/28.
 */
public class DemoServiceImpl implements com.glt.dubbo.DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
