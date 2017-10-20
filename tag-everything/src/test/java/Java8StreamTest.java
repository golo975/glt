import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 下面的练习基于以下文章
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class Java8StreamTest {

    @Test
    public void stream() {
        List<Integer> list = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);

        System.out.println(list);

        // 1. 从list得到Stream
        Stream<Integer> stream = list.stream();
        // 2. 过滤Stream
        Stream<Integer> filterStream = stream.filter(Objects::nonNull);
        // 3. 从Stream得到list
        List<Integer> filterList = filterStream.collect(Collectors.toList());
        System.out.println(filterList);
    }
    
    @Test
    public void ScopeTest() {
        Person p1 = new Person(1, "p1");
        Person p2 = new Person(2, "p2");
        List<Person> list = Arrays.asList(p1, p2);
        Map<String, Person> map = new HashMap<>();

        System.out.println(map);
        Consumer<Person> consumer = person -> map.put(person.getName(), person);
        list.forEach(consumer);
        System.out.println(map);
    }

    @Test
    public void intStreamTest() {
        IntStream.of(1, 2, 3, 4, 5).forEach(System.out::print);
        System.out.println();
        IntStream.range(1, 5).forEach(System.out::print);//左闭右开区间
        System.out.println();
        IntStream.rangeClosed(1, 5).forEach(System.out::print);//闭区间
        System.out.println();
    }

    @Test
    public void stream2otherTest() {

        {
            // 1. Stream 转数组
            Stream stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            Object[] array = stream.toArray();
            System.out.println(Arrays.toString(array));
        }

        {
            // 2. Stream 转List
            Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            List list1 = stream.collect(Collectors.toList());
            System.out.println(list1);

            // Collectors.toCollection(Supplier<T>)中，Supplier 是一个函数式接口，下面以方法引用的方式创建。
            stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            List list2 = stream.collect(Collectors.toCollection(ArrayList::new));
            System.out.println(list2);

            /*
            1. Collector : Collector 就是一个专门用于将Stream中的数据“收集”到其他对象中去的对象,
                动态的聚合操作，例如：
                    1. 将数据输出到Collection；
                    2. 使用StringBuilder拼接字符串；
                    3. 计算sum，min, max, average 等总体值；
                Collectors 提供了很多通用的 Collector 的实现类。
                Collector有4个主要的方法：
                    1. supplier(): 创建一个新的 result container；
                    2. accumulator: 向 result container 中添加元素；
                    3. combiner: 将两个 result container 合二为一；
                    4. finisher: 在 result container 上执行一个可选的转换操作；

            2. Supplier<T> 是一个函数式接口，其中只有一个方法：T get();
             */
        }

        {
            Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            Set set1 = stream.collect(Collectors.toSet());
            System.out.println(set1);

            stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            Set set2 = stream.collect(Collectors.toCollection(HashSet::new));
            System.out.println(set2);
        }

        {
            Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            Stack stack = stream.collect(Collectors.toCollection(Stack::new));
            System.out.println(stack);
        }

        {
            // 注意：stream中的元素必须都是String类型的，才能进行拼接字符串的操作。
            Stream<String> stream = Stream.of("1", "1", "0", "2", "3", "4", "6", "7", "9", "6", "0");
            String string = stream.collect(Collectors.joining());
            System.out.println(string);
        }
    }

    @Test
    public void CollectorTest() {
        stream2otherTest();
    }

    @Test
    public void CollectorGroupingByTest() {
        Map<Integer, List<Integer>> integerListMap = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6)
                .distinct()
                .boxed()
                .collect(Collectors.groupingBy(i -> i % 3));
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = integerListMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> next = iterator.next();
            System.out.println("key=" + next.getKey() + ", value=" + next.getValue());
        }
    }

    /**
     * PartitionBy其实使一种特殊的GroupingBy，
     * PartitionBy只有true和false 2中分组
     */
    @Test
    public void CollectorPartitioningByTest() {
        Map<Boolean, List<Integer>> booleanListMap = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6)
                .distinct()
                .boxed()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println("even:" + booleanListMap.get(true));//偶数
        System.out.println("odd:" + booleanListMap.get(false));//奇数
    }

    @Test
    public void mapFlatMapTest() {
        {
            // map()方法的作用是把stream中的元素映射（或曰转换）为新的值，构成新的stream，元素前后是一一映射的。
            Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
            Stream<Integer> outStream = stream.map(i -> i * i);
            System.out.println(outStream.collect(Collectors.toList()));
        }

        {
            // flatMap()方法：原stream中的元素是类似集合的对象，即原stream中的一个元素映射多个输出元素
            Stream<List<Integer>> listStream = Stream.of(
                    Arrays.asList(1, 1),
                    Arrays.asList(2, 3),
                    Arrays.asList(4, 5, 6));
            Stream<Integer> outStream = listStream.flatMap(List::stream);
            System.out.println(outStream.collect(Collectors.toList()));
        }

    }

    @Test
    public void filterTest() {
        Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
        Object[] outStream = stream.filter(i -> i != 0).toArray();
        System.out.println(Arrays.toString(outStream));
    }

    @Test
    public void forEachTest() {
        Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
        // forEach()是terminal操作，执行此方法后，stream中的元素就会被消费掉。
        // 无法对一个Stream进行两次terminal运算。
        // forEach()不能操作自己包含的本地变量值，也不能使用break/return之类的关键字提前结束循环。
        stream.forEach(System.out::print);
    }

    @Test
    public void peekTest() {
        Stream<Integer> stream = Stream.of(1, 1, 0, 2, 3, 4, 6, 7, 9, 6, 0);
        // peek 和 forEach的功能类似，但是peek不是terminal操作，peek()执行后会生成新的stream对象。
        // 需要注意的是，peek()中的操作，只有在执行到terminal操作时才会被真正执行。
        // 例如下面的例子，如果没有最后的collect()方法，是不会打印数据的。
        Stream<Integer> outStream = stream.peek(System.out::print);
        System.out.println(outStream.collect(Collectors.toList()));
    }

    @Test
    public void optionalTest() {
        /*
        empty()方法：
            创建一个包含空值的Optional对象。
         */
        Optional<Object> empty = Optional.empty();
        /*
        isPresent()方法：
            如果当前Optional对象描述的对象t为空，返回true
         */
        System.out.println(empty.isPresent());
         /*
         of(T t)方法：
             这是一个工厂方法，用于从某值创建出一个Optional对象
             具体地说，该方法返回一个描述t的Optional对象，t必须是非空的，否则会导致NPE
         ofNullable(T t)方法：
             和of(T t)类似，区别在于不会抛出NPE，而是返回一个表示空值的Optional对象(isPresent()==true).
         */
        Optional<Optional<Object>> notEmpty = Optional.ofNullable(empty);
        System.out.println(notEmpty.isPresent());
        /*
        ifPresent(Consumer<? super T>)方法：
            如果不为空，执行Consumer中定义的操作
         */
        notEmpty.ifPresent(System.out::print);
        /*
        get()方法：
            用于获取Optional中的值
         */
        Optional<String> nonNullOptional = Optional.of("string");
        System.out.println(nonNullOptional.get());
        /*
        orElse()方法：
            如果Optional中的值为空，返回else中的值，否则返回Optional中的值
         */
        Object anElse = empty.orElse("else");
        /*
        orElseGet(Supplier)方法：
            和orElse()方法作用相同，区别在于使用Supplier来创建else的值
         */
        empty.orElseGet(Object::new);
        /*
        map(Function mapper)方法：
            如果Optional中的值不为空，执行映射方法（即map()方法的入参Function对象）
                如果映射后的结果为空，返回一个包含该值的Optional对象；
                如果映射后的结果不为空，返回一个空值Optional对象；
                （从源码中可以看出，其实就是对映射后的值使用了Optional.ofNullable()方法；
            如果Optional中的值为空，返回一个包含空的Optional对象，相当于map()方法什么也没做；
            （不过并非返回当前值，而是创建了一个新的空值Optional对象，这是为什么？）
        配合使用map()和orElse()，相当于执行了if-else逻辑。
         */
        String s1 = Optional.ofNullable(null).map(Object::toString).orElse("--");
        String s2 = Optional.ofNullable("null").map(Object::toString).orElse("");
        Integer integer1 = Optional.ofNullable(null).map(Object::hashCode).orElse(-1);
        Integer integer2 = Optional.ofNullable("null").map(Object::hashCode).orElse(-1);
        System.out.println(s1 + " " + s2 + " " + integer1 + " " + integer2);
        /*
        orElseThrow(Supplier<? extends Throwable>)方法：
            与orElse()的逻辑类似，如果Optional中的值不为空，返回；如果为空，就抛出由Supplier创建的异常。
         */
        empty.orElseThrow(RuntimeException::new);
        // TODO: 2017/9/13 关于Optional，还有filter()，flatMap()等方法，未完待续。。。
    }

    @Test
    public void findFirstTest() {
        Optional<Integer> first = Stream.of(1, 2, 3, 4).findFirst();
        System.out.println(first.get());

        Optional<Object> emptyFirst = Stream.empty().findFirst();
        System.out.println(emptyFirst.orElse("null"));
        emptyFirst.ifPresent(System.out::println);
        System.out.println(emptyFirst.map(Object::toString).orElse("null"));
    }

    @Test
    public void reduceTest() {
        Integer min = Stream.of(3, 1, 4, 1, 5, 9, 2, 6).reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println(min);

        /*
        Optional<T> reduce(BinaryOperator<T> accumulator)
         */
        Optional min2 = Stream.of(1).reduce(Integer::min);

        OptionalInt min3 = IntStream.of().reduce(Integer::min);
        System.out.println(min3.orElse(Integer.MAX_VALUE));

        Integer sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sum);
        Optional<Integer> sumOptional = Stream.of(1, 2, 3, 4).reduce(Integer::sum);
        System.out.println(sumOptional.get());
        sumOptional.ifPresent(System.out::println);
        System.out.println(sumOptional.orElse(0));
        // 注意区分下面的 +1，前++，后++ 三者的区别
        // Stream.iterate()是惰性加载的，每个元素的生成过程都是独立的，不同元素之间不会互相影响
        // 对于item -> item++, 相当于每次生成元素是执行的是 (((item++)++)++ ...)++ ，最后的结果是 item
        // 对于item -> ++item, 相当于每次生成元素是执行的是 ++(... ++(++(++item))) ，最后的结果是 item + N
        Integer sum100_1 = Stream.iterate(1, item -> item + 1).limit(100).reduce(0, Integer::sum);
        System.out.println(sum100_1);// ==> 5050
        Integer sum100_2 = Stream.iterate(1, item -> item++).limit(100).reduce(0, Integer::sum);
        System.out.println(sum100_2);// ==> 100
        Integer sum100_3 = Stream.iterate(1, item -> ++item).limit(100).reduce(0, Integer::sum);
        System.out.println(sum100_3);// ==> 5050

        Optional<String> concat = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(item -> item.compareTo("Z") > 0)// 过滤掉大写，值保留小写
                .reduce(String::concat);
        System.out.println(concat.orElse(""));
    }

    /**
     * limit：截取前 N 个元素
     */
    @Test
    public void limitTest() {
        Stream.iterate(0, i -> ++i).limit(10).forEach(System.out::print);
         /*
         注意：limit会限制实际运行过程中影响到的Stream中的元素的个数，
         例如，stream.map().limit(n),假设stream中的元素个数原本是 > n 的，
         但是因为有 limit() 方法，真正执行 map() 操作的元素依然只有 n 个。
         但是这也有一个例外：stream.sorted().limit(n),
         此时，因为排序操作需要对所有元素进行比较才能得到结果，
         所以，即使此时使用了limit(n)方法，执行sorted的元素依然是所有的元素
         */
    }

    /**
     * skip: 跳过前 N 个元素
     */
    @Test
    public void skipTest() {
        Stream.iterate(0, i -> ++i)
                .skip(10)
                .limit(10)
                .forEach(i -> System.out.print(i + ", "));
    }

    @Test
    public void IntSummaryStatisticsTest() {
        IntSummaryStatistics statistics = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6).summaryStatistics();
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
        System.out.println(statistics.getAverage());
        System.out.println(statistics);
    }

    /**
     * Stream 的sorted方法比数组的排序更强大，
     * 因为Stream在排序之前可以先执行 map, filter, limit, skip, distinct 等方法来减少元素的数量，
     * 这样可以明显缩短排序的执行时间
     */
    @Test
    public void sortedTest() {
        List<Person> persons = new ArrayList<>();
        Random random = new Random(47);
        for (int i = 1; i <= 10; i++) {
            Person person = new Person(i, "name" + random.nextInt(10));
            persons.add(person);
        }
//        List<Person> sortedPersons = persons.stream()
//                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
//                .collect(Collectors.toList());
        List<Person> sortedPersons = persons.stream()
                .sorted(Comparator.comparing(Person::getName))// 注意Comparator接口新增的static方法
                .collect(Collectors.toList());
        System.out.println(sortedPersons);
    }

    @Test
    public void minTest() {
        OptionalInt min = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6).min();
        System.out.println(min.orElse(Integer.MAX_VALUE));
    }

    @Test
    public void maxTest() {
        OptionalInt max = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6).max();
        System.out.println(max.orElse(Integer.MIN_VALUE));
    }

    @Test
    public void distinctTest() {
        IntStream distinctStream = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6).distinct();
        // IntStream 中的元素是基本类型的int，转换成List需要转换为包装类型的Integer，所以需要使用boxed()方法
        List<Integer> list = distinctStream.sorted().boxed().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 有 3 个match方法：
     * 1. allMatch:  所有条件都为    true 时， 结果为 true
     * 2. anyMatch:  至少有一个条件为 true 时， 结果为 true
     * 3. noneMatch: 所有条件都为    false 时，结果为 true
     */
    @Test
    public void matchTest() {
        {
            boolean allMatch = Stream.of(true, false).allMatch(b -> b);
            boolean anyMatch = Stream.of(true, false).anyMatch(b -> b);
            boolean noneMatch = Stream.of(true, false).noneMatch(b -> b);
            System.out.println(allMatch);
            System.out.println(anyMatch);
            System.out.println(noneMatch);
        }
        {
            boolean allMatch = Stream.of(false, false).allMatch(b -> b);
            boolean anyMatch = Stream.of(false, false).anyMatch(b -> b);
            boolean noneMatch = Stream.of(false, false).noneMatch(b -> b);
            System.out.println(allMatch);
            System.out.println(anyMatch);
            System.out.println(noneMatch);
        }
        {
            boolean allMatch = Stream.of(true, true).allMatch(b -> b);
            boolean anyMatch = Stream.of(true, true).anyMatch(b -> b);
            boolean noneMatch = Stream.of(true, true).noneMatch(b -> b);
            System.out.println(allMatch);
            System.out.println(anyMatch);
            System.out.println(noneMatch);
        }
    }

    /**
     * 注意：一定要limit方法，否则会陷入死循环
     */
    @Test
    public void generateTest() {
        Random random = new Random(47);
//        Supplier<Integer> supplier = random::nextInt;
        Supplier<Integer> supplier = () -> random.nextInt(10);// 也可以自己定义Supplier的子类
        Stream.generate(supplier).limit(10).sorted().forEach(System.out::println);
    }

    /**
     * 注意：一定要limit方法，否则会陷入死循环
     */
    @Test
    public void iterateTest() {
        Stream.iterate(0, i -> ++i).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Optional<String> iterateReduce = Stream.iterate(0, i -> ++i)
                .limit(10)
                .map(i -> i + " ")
                .reduce(String::concat);
        System.out.println(iterateReduce.orElse(""));
    }

    /*

    Stream 的特性可以归纳为：
        1. 不是数据结构
        2. 它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。
        3. 它也绝不修改自己所封装的底层数据结构的数据。
           例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。
        4. 所有 Stream 的操作必须以 lambda 表达式为参数
        5. 不支持索引访问
        6. 你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项。
        7. 很容易生成数组或者 List
        8. 惰性化
        9. 很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。
        10. Intermediate 操作永远是惰性化的。
        11. 并行能力
        12. 当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。
        13. 可以是无限的
        14. 集合有固定大小，Stream 则不必。
            limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。
     */


    private static class Person {
        public int no;
        private String name;

        public Person(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public String getName() {
            System.out.println(name);
            return name;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "{no=" + no + ",name=" + name + "}";
        }
    }

}

