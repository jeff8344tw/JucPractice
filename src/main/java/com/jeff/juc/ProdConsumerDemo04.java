package com.jeff.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 題目: 現在兩個線程，可以操作初始值為零的一個變量
 * 實現一個線程對該變量加1，一個線程對該變量減1
 * 實現交替，來10輪，變量初始值為零
 * 1. 高內聚低耦合的前提下，線程操作資源類
 * 2. 判斷 幹活 通知
 * 3. 防止虛假喚醒
 * <p>
 * 知識小總結 = 多線程編程的套路+while判斷+新版寫法
 */
public class ProdConsumerDemo04 {

    public static void main(String[] args) throws Exception {
        AirCondition airCondition = new AirCondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class AirCondition {

    private int number = 0;

    // 可重入非公平的遞規鎖
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1.判斷
            while (number != 0) {
//            this.wait();
                condition.await();
            }
            // 2.幹活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3.通知
//        this.notifyAll();
            condition.signalAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1.判斷
            while (number == 0) {
//            this.wait();
                condition.await();
            }
            // 2.幹活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3.通知
//        this.notifyAll();
            condition.signalAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

//    public synchronized void increment() throws Exception {
//        // 1.判斷
//        while (number != 0) {
//            this.wait();
//        }
//        // 2.幹活
//        number++;
//        System.out.println(Thread.currentThread().getName() + "\t" + number);
//        // 3.通知
//        this.notifyAll();
//    }
//
//    public synchronized void decrement() throws Exception {
//        // 1.判斷
//        while (number == 0) {
//            this.wait();
//        }
//        // 2.幹活
//        number--;
//        System.out.println(Thread.currentThread().getName() + "\t" + number);
//        // 3.通知
//        this.notifyAll();
//    }
}
