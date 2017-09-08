import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * stable sort：插入排序、冒泡排序、归并排序、计数排序、基数排序、桶排序。
 * unstable sort：选择排序(5 8 5 2 9)、快速排序、堆排序。
 */
public class BubbleSortTest {
    @Test
    public void bubbleSortTest() {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(1000));
        }
        System.out.println(list);

        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < list.size() - j; i++) {
                if (i + 1 < list.size() - j) {
                    Integer cursor = list.get(i);
                    Integer cursor2 = list.get(i + 1);
                    if (cursor < cursor2) {
                        list.set(i + 1, cursor);
                        list.set(i, cursor2);
                    }
                }
            }
        }

        System.out.println(list);

    }
}