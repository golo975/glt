package com.glt.springboot.test2;

import com.alibaba.dubbo.config.annotation.Service;
import com.glt.dubbo.test.api.EchoService;

@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) {
        return "Hello World From test2.";
    }
}
