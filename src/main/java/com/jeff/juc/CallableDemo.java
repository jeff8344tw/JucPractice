package com.jeff.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * java
 * 多線程中，第三種獲得多線程的方式
 * 1. get方法一般請放在最後一行
 * 2. 當多個線程調用FutrueTack裡方法時，他只會調用一次
 */
public class CallableDemo {

    public static void main(String[] args) throws  Exception {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        System.out.println(Thread.currentThread().getName() +" 計算完成");

        Integer result = futureTask.get();
        System.out.println(result);
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call method");
        // 暫停一會兒線程
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 1024;
    }
}
