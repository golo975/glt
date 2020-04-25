package com.gaolong.springbootdemo1;

import java.nio.channels.ClosedSelectorException;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {

    public static void main(String[] args) {
        SimpleFibonacci simpleFibonacci = new SimpleFibonacci(10);
        Integer compute = simpleFibonacci.compute();

        for (int i = 0; i < 10; i++) {
            System.out.println(new Fibonacci(i + 1).compute());
        }


    }

//    public static class BigTask extends RecursiveTask<Long> {
//
//        @Override
//        protected Long compute() {
//            return null;
//        }
//
//    }
//
//    public static class BigTaskNoReturn extends RecursiveAction {
//
//        @Override
//        protected void compute() {
//
//        }
//
//    }

    public static class SimpleFibonacci {

        final int n;

        SimpleFibonacci(int n) {
            this.n = n;
        }

        Integer compute() {
            int before1 = 1;
            int before2 = 2;
            if (n == 1) {
                System.out.println(1);
                return 1;
            }
            if (n == 2) {
                System.out.println(1);
                return 1;
            }

            for (int i = 2; i < n; i++) {
                int current = before1 + before2;
                System.out.println(current);
                before1 = before2;
                before2 = current;

                if (i == n - 1) {
                    return current;
                }
            }

            return 0;
        }
    }

    public static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();

            Fibonacci f2 = new Fibonacci(n - 2);
            //等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }


}
