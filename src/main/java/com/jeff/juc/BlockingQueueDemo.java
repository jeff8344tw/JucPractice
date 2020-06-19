package com.jeff.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞對列
 *
 * 1. 兩個數據結構: 棧/隊列
 *  1.1 棧 後進先出
 *  1.2 隊列 先進先出
 *
 * 2. 阻塞隊列
 *  2.1 阻塞 必須要阻塞/不得不阻塞
 *
 *  ArrayBlockingQueue: 由數組結構組成的有界阻塞對列
 *  LinkedBlockingQueue: 由連表結構組成的有界(但大小默認值為integer.MAX_VALUE) 阻塞對列
 *  PriorityBlockingQueue: 支持優先級排序的吳介阻塞對列
 *  DelayQueue: 使用優先級對列實現的延遲無界阻塞對列
 *  SynchronousQueue: 不儲存完訴的阻塞對列，也級單個元素的對列
 *  LinkedTransferQueue: 由鏈表組成的吳界阻塞對列
 *  LinkedBlockingDeque: 由鏈表組成的雙向阻塞對列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*
        // add插入:拋出異常
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("x"));
        // remove移除:拋出異常
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
         */
        /*
        // offer插入:成功true失敗false
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        // 移除方法:成功返回對列的元素，對列裡沒有返回NULL
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        */
        // 一直阻塞:put插入:若超出範圍則等待有空位
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("x");
        // 一植阻塞:take移除:若超出範圍則等待有新值
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
        // 超時退出
        System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));

    }
}
