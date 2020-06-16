package com.jeff.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信號燈
 *
 * 在信號量上我們定義兩種操作:
 * acquire(獲取) 當一個線程調用acquire操作時，他要麼通過成功獲取信號量(信號減1)，
 *    要麼一直等下去，直到有線程釋放信號量，或超時。
 * release(釋放) 實際上會將信號量的值加1，然後喚醒等待的線程
 *
 * 信號量主要用於兩個目的，一個是用於多個共享資源的互斥使用，一個用於並發線程樹的控制。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 模擬資源類有三個空車位
        Semaphore semaphore = new Semaphore(3);

        for(int i = 1; i <= 6; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t佔到了車位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t離開了車位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
