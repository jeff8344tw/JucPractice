package com.jeff.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 題目: 三個售票員 賣出 30張票
 * 筆記: 如何編寫企業級的多線程代碼
 * 固定的編程套路+模板示什麼?
 * 1.在高內聚低耦合的前提下，線程 操作 資源類
 * <p>
 * 1.1 先創建一個資源類
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) { // 主線程，一切程序的入口
        final Ticket ticket = new Ticket();

        new Thread(() -> { for(int i = 1; i <= 40; i++) ticket.sale(); },"A").start();
        new Thread(() -> { for(int i = 1; i <= 40; i++) ticket.sale(); },"B").start();
        new Thread(() -> { for(int i = 1; i <= 40; i++) ticket.sale(); },"C").start();

        // new interface = 匿名內部類
        /**
        new Thread(new Runnable() {
            public void run() {
                for(int i = 1; i <= 40; i++){
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            public void run() {
                for(int i = 1; i <= 40; i++){
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            public void run() {
                for(int i = 1; i <= 40; i++){
                    ticket.sale();
                }
            }
        }, "C").start();
        */
    }
}

class Ticket { // 資源類 = 實例變量 + 實例方法
    private int number = 30;
    // 可重複鎖
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t賣出第: " + (number--) + "\t還剩下: " + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
