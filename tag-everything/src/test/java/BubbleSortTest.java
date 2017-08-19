import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/19.
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
