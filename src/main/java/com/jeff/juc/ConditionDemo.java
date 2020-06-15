package com.jeff.juc;

import javax.security.auth.login.Configuration;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 備註: 多現成之間按順序調用，實現A->B->C
 * 三個線程啟動，要求如下
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 持續10輪
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for(int i = 1; i <= 10; i++){
                shareData.print5();
            }
        },"A").start();
        new Thread(() -> {
            for(int i = 1; i <= 10; i++){
                shareData.print10();
            }
        },"B").start();
        new Thread(() -> {
            for(int i = 1; i <= 10; i++){
                shareData.print15();
            }
        },"C").start();
    }
}

class ShareData {
    private int number = 1; // A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知c2
            number = 2;
            c2.signal();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            for(int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 3;
            c3.signal();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            for(int i = 0; i < 15; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 1;
            c1.signal();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
