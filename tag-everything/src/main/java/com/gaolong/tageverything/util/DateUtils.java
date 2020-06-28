package com.gaolong.tageverything.util;

import java.time.*;

public class DateUtils {
    public static void main(String[] args) {
        {
            String s = "2018-09-22T12:30:10Z";// 说明：这里的Z应该是zero的意思，代表0时区，即当前字符串表示的是0时区的dateTime
            {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(s);
                System.out.println(zonedDateTime);
                System.out.println("----");
                System.out.println(zonedDateTime.toLocalDate());
                System.out.println(zonedDateTime.toLocalTime());
                System.out.println(zonedDateTime.toLocalDateTime());
                System.out.println(zonedDateTime.toOffsetDateTime());
                System.out.println("----");
                System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()));
                System.out.println(zonedDateTime.withZoneSameLocal(ZoneId.systemDefault()));
            }
            System.out.println("============");
            {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(s);
                System.out.println(offsetDateTime);
                System.out.println("----");
                System.out.println(offsetDateTime.toLocalDate());
                System.out.println(offsetDateTime.toLocalTime());
                System.out.println(offsetDateTime.toLocalDateTime());
                System.out.println(offsetDateTime.toZonedDateTime());
                System.out.println("----");
                System.out.println(offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()));
                System.out.println(offsetDateTime.atZoneSimilarLocal(ZoneId.systemDefault()));
            }
        }
        System.out.println();
        System.out.println("= * = * = * = * = * =");
        System.out.println();
        {
            String s = "2018-09-22T12:30:10";
            LocalDateTime localDateTime = LocalDateTime.parse(s);
            System.out.println(localDateTime);
        }
    }


}
