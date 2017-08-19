/**
 * Created by Administrator on 2017/8/7.
 */
public class Java8LambdaTest {
    interface MathOperation {
        int operation(int a, int b);
    }

    // 声明入参类型
    private static MathOperation addition = (int a, int b) -> {
        return a + b;
    };
    // 不声明入参类型
    private static MathOperation subtraction = (a, b) -> {
        return a - b;
    };

    private static MathOperation multiplication = (a, b) -> {
        return a * b;
    };
    // 不使用大括号和return
    private static MathOperation division = (a, b) -> a / b;

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {

        System.out.println(Java8LambdaTest.operate(1, 1, addition));
        System.out.println(Java8LambdaTest.operate(1, 1, subtraction));
        System.out.println(Java8LambdaTest.operate(1, 1, multiplication));
        System.out.println(Java8LambdaTest.operate(1, 1, division));

        System.out.println(addition.operation(1, 1));
    }


}