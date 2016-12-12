package com.glt;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by gaolong on 2016/12/8.
 */
public class Test {

    public static void main(String[] args) throws Exception{
        File full = FileUtils.getFile("/Users/gaolong/Downloads/", "push_alert.log.2016-12-07");
        File target = FileUtils.getFile("/Users/gaolong/Downloads/", "apns.log");
        List<String> allLines = FileUtils.readLines(full);
        long sum = 0;

        List<String> strings = FileUtils.readLines(target);
        for (String line : strings) {
            String token = StringUtils.substringBetween(line, "taskId:", ",");
            if (StringUtils.isNotBlank(token)) {
                for (String l : allLines) {
                    if (l.contains(token)) {
                        System.out.println(l);
                        String[] split = StringUtils.split(l, "总数：");
                        int i = Integer.valueOf(split[3]);
                        sum += i;
                    }
                }
            }
        }

        System.out.println("总数: " + sum);
    }
}
