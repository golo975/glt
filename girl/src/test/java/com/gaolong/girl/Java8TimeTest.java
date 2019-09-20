package com.gaolong.girl;

import org.assertj.core.data.Offset;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Java8TimeTest {

    @Test
    public void test0() {
        Map<String, List<ChronoUnit>> collect = Arrays.stream(ChronoUnit.values())
                .collect(groupingBy(unit -> {
                    if (unit.isDateBased() && unit.isTimeBased()) {
                        return "DateTime";
                    } else {
                        if (unit.isDateBased()) {
                            return "Date";
                        }
                        if (unit.isTimeBased()) {
                            return "Time";
                        }
                    }
                    return "non-non";
                }));
        System.out.println(collect);
    }


    @Test
    public void test1() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        OffsetDateTime offsetDateTime = instant.atOffset(OffsetDateTime.now().getOffset());

        LocalDate localDate = zonedDateTime.toLocalDate();
        LocalTime localTime = zonedDateTime.toLocalTime();
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        localDateTime.toLocalDate();
        localDateTime.toLocalTime();

        localDate.atTime(1, 1);

        localDateTime.atZone(ZoneId.systemDefault());

        zonedDateTime.toInstant();

        Date.from(instant);


    }

    @Test
    public void collectorsTest() {
        Map<Integer, Integer> stringMap = Stream.of("a", "b", "aa", "bb", "cc").collect(groupingBy(String::length, collectingAndThen(toList(), List::size)));
        System.out.println(stringMap);
    }
}
