package com.jeff.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 讀寫鎖:
 * 多個線程同時讀一個資源沒有任何問題，所以為了滿足併發量，讀取共享資源應該可以同時進行
 * 但是
 * 如果有一個線程想去寫共享資源，就不應該再有其他縣城可以對該資源進行讀或寫
 * 小總結:
 *      讀-讀能共存
 *      讀-寫不能共存
 *      寫-寫不能共存
 */
public class ㄐReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for(int i = 1; i <= 5; i++){
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.put(tempInt+"", tempInt + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for(int i = 1; i <= 5; i++){
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.get(tempInt+"");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value){

        // 寫鎖
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"\t 開始寫入"+key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 寫入完成");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    public void get(String key) {
        // 讀鎖
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"\t 開始讀取"+key);
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 讀取完成"+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }


    }
}
