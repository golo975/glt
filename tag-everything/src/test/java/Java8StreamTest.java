import com.google.common.collect.Lists;

import java.util.List;

public class Java8StreamTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);

        int sum = list.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::println)
                .skip(2)
                .limit(4)
                .sum();
        System.out.println("sum is:" + sum);
    }

}

