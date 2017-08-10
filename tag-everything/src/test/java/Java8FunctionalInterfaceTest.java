package test.java;

import java.util.function.Function;

public class Java8FunctionalInterfaceTest {

    interface FunctionTest1 extends Function{
        @Override
        default Function compose(Function before) {
            System.out.println("FunctionTest1");
            return compose(before);
        }
    }

    interface FunctionTest2 extends Function{
        @Override
        default Function compose(Function before) {
            System.out.println("FunctionTest2");
            return compose(before);
        }
    }

    static class FunctionTestClass implements FunctionTest2, FunctionTest1{

        @Override
        public Object apply(Object o) {
            return null;
        }

        @Override
        public Function compose(Function before) {
            FunctionTest2.super.compose(before);
            return null;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }

}

