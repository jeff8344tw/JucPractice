package com.jeff.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        // 倒計時
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i = 1; i <= 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+"離開教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        // 堵塞 須等到countDownLatch為0才釋放
        countDownLatch.await();
        System.out.println("班長關門走人");
    }

    public static void closeDoor() {
        for(int i = 1; i <= 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+"離開教室");
            },String.valueOf(i)).start();
        }

        System.out.println("班長關門走人");
    }
}
