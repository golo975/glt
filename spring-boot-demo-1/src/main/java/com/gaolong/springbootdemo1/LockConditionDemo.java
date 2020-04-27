package com.gaolong.springbootdemo1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();


        // todo 一下三个方法，正式为了弥补 synchronized 关键字的不足：如果获取不到锁，就直接阻塞，没有任何外部方法可以改变阻塞状态
        lock.lockInterruptibly();// 1. 阻塞时，可以相应终端
        lock.tryLock();// 2. 尝试获取锁，不管是否获取到都直接返回，获取到了返回 true，没获取到就返回 false
        lock.tryLock(1, TimeUnit.SECONDS);// 3. 在一定时限内，如果获取不到就返回



        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();

        // todo 下面3个方法，作用分别对应 Object 中的 wait(), notify(), notifyAll() 3 个方法，效果是一样的
        notFull.await();
        notFull.signal();
        notFull.signalAll();
        // Lock&Condition 实现的管程里，只能使用上面3个方法

        // todo -------------------------------------------------------------

        // todo 下面3个方法都是集成自 Object ，此处应无视他们
        notFull.wait();
        notFull.notify();
        notFull.notifyAll();
        // synchronized 实现的管程中，只能使用上面3个方法

        // todo -------------------------------------------------------------

        /*
        todo 总结：
         Lock&Condition 和 synchronized 是 2 种不同的实现 管程 的方法，
         针对这 2 种管程，分别有对应的配套方法，
         这些配套方法 **不可以混用，否则后果非常严重！！**
         */

    }


}
