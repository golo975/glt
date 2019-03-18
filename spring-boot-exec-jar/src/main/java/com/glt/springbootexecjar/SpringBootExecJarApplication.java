package com.glt.springbootexecjar;

import ch.qos.logback.core.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@SpringBootApplication
public class SpringBootExecJarApplication implements CommandLineRunner {

    public static void main(String[] args) {
//        SpringApplication.run(SpringBootExecJarApplication.class, args);

//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = LocalDate.of(2018, 12, 31);
//        Period period = Period.between(startDate, endDate);
//        int days = period.getDays();
//        System.out.println(days);

        /*LocalTime startTime = LocalTime.of(1, 1);
        LocalTime endTime = LocalTime.of(6, 2);
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();
        System.out.println(seconds);

        Map<Boolean, ArrayList<String>> map = new HashMap<Boolean, ArrayList<String>>() {
            {
                {
                    {
                        put(Boolean.TRUE, new ArrayList<>());
                        put(Boolean.FALSE, new ArrayList<>());
                    }
                }
            }
        };*/


        new SpringBootExecJarApplication().test();


    }

    public void test() {


        File file = FileUtils.getFile("C:\\Users\\longx\\OneDrive\\ing\\the_dark_forest.txt");
        try {
            List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());

            Map<String, Long> wordCountMap = lines.stream()
                    .map(line -> line.split(" "))
                    .flatMap(Arrays::stream)
                    .map(s -> s.replaceAll("[“.*,…]", ""))
                    .filter(StringUtils::isNotBlank)
                    .filter(s -> !ExcludeWord.exclude.contains(s))
                    .collect(groupingBy(Function.identity(), counting()));

            LinkedHashMap<String, Long> collect = wordCountMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (oldValue, newValue) -> oldValue,
                                    LinkedHashMap::new));

            collect.entrySet().stream()
//                    .skip(ExcludeWord.skip.size())
                    .skip(2000)
                    .limit(20)
                    .forEach(entry -> System.out.println("\"" + entry.getKey() + "\"," + entry.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run(String... args) throws Exception {
        /*
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

//        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction("abcdefghijklmnopqrstuvwxyz");
//        forkJoinPool.submit(customRecursiveAction);
//        customRecursiveAction.join();


//        CustomRecursiveTask customRecursiveTask = new CustomRecursiveTask(IntStream.rangeClosed(1, 50).toArray());
//        Integer result = forkJoinPool.invoke(customRecursiveTask);
//        System.out.println(result);

        */


    }


}
