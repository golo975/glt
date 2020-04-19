package com.gaolong.springbootdemo1;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    public static void main(String[] args) {
        Point point = new Point();
        point.setX(3);
        point.setY(4);
        System.out.println(point.distanceFromOrigin());
    }

    public static class Point {
        private int x, y;
        final StampedLock stampedLock = new StampedLock();

        int distanceFromOrigin() {
            long stamp = stampedLock.tryOptimisticRead();

            int _x = x, _y = y;
            if (!stampedLock.validate(stamp)) {
                long stamp1 = stampedLock.readLock();
                try {
                    _x = x;
                    _y = y;
                } finally {
                    stampedLock.unlockRead(stamp1);
                }
            }

            return (int) Math.sqrt(_x * _x + _y * _y);
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
