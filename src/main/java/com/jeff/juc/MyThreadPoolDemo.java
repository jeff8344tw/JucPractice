package com.jeff.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1.corePoolSize: 線程池中的常駐核心線程數
 * 2.maximumPoolSize: 線程池中能夠容納同時同時執行的最大線程數，此值必須大於等於1
 * 3.keepAliveTime: 多餘的空嫌線程的存活時間，當前池中線程數量超過corePoolSize時，
 * 達到keepAliveTime時，多餘線程會被銷毀值到只剩下corePoolSize個線程為止
 * 4.unit: keepAliveTime的單位
 * 5.workQueue: 任務對列，被提交但尚未被執行的任務
 * 6.threadFactory:表示生成線程池中的工作線程的線程工廠，
 * 用於創建線程，一般默認即可
 * 7.handler: 拒絕策略，表示當對列滿了，並且工作線程大於等於線程池的最大線程時(maximumPoolSize)
 * 時如何來拒絕請求執行的runnable的策略
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        // 一池5個工作線程，類似一個銀行有五個受理窗口
        /**
         * 執行長期任務性能好，創建一個線程池，
         * 一池有N個固定的線程，有固定線程數的線程
         */
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一池5個工作線程，類似一個銀行有一個受理窗口
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        /**
         * 執行很多短期異步任務，線程池根據需要創建新線程，
         * 但在先前創建的線程可用時將重用他們，可擴容，遇強則強
         */
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
             // 模擬有10個顧客過來銀行辦理業務，目前池子裡面有5個工作人員提供服務
            for (int i = 0; i < 10; i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "辦理業務");
                });
            }
            TimeUnit.MILLISECONDS.sleep(400);

        } catch (Exception e) {

        } finally {
            threadPool.shutdown();
        }
    }
}
