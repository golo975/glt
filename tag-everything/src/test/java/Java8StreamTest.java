import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        /*int sum = list.stream()
                .filter(Objects::nonNull)
                .distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::println)
                .skip(2)
                .limit(4)
                .sum();
        System.out.println("sum is:" + sum);*/
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
            1. Collector : Collector 就是一个专门用于将Stream中的数据“收集”到其他对象中去的对象
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
        // forEach()是terminal操作，执行此方法后，stream中的元素就会被消费掉。无法对一个Stream进行两次terminal运算。
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
        String s = "s";
        Optional.ofNullable(s).ifPresent(System.out::println);
    }

}

