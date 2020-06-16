package com.jeff.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 循環屏障
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        // public CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {System.out.println("召喚神龍");});

        for(int i = 1; i <= 7; i++){
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t收集到第: " + tempInt + "顆龍珠");
                try{
                    cyclicBarrier.await();
                } catch (Exception e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
