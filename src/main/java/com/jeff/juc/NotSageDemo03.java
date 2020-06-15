package com.jeff.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 故障現象
 *      java.util.ConcurrentModificationException
 * 2. 導致原因
 * 3. 解決方法
 *      3.1 new Vector<>()
 *      3.2 Collections.synchronizedList(new ArrayList<>())
 *      3.3 new CopyOnWriteArrayList()
 * 4. 優化建議(同樣的錯誤不犯第二次)
 */
public class NotSageDemo03 {
    public static void main(String[] args) {
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for(int i = 1; i <= 30; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 筆記
 * 寫時複製
 * CopyOrWrite容器及寫時複製的容器，往一個容器添加元素的時候，不直接往當前容器Object[]添加，而是先將當前容器Object[]進行copy
 * 複製出一個新的容器Object[] newElements, 然後新的容器Object[] newElements裡添加元素，添加完元素之後，
 * 再將原容器的引用指向新的容器 setArray(newElements);。這樣做的好處是可以對CopyOrWrite容器進行併發的讀，
 * 而不需要加鎖，因為當前容器不會添加任何元素，所以CopyOrWrite容器也是一種讀寫分離的思想，讀和寫不同的容器
 */
//public boolean add(E e){
//    final ReentrantLock lock = this.lock;
//    lock.lock();
//
//    try {
//        Object[] elements = getArray();
//        int len = elements.length;
//        Object[] newElements = Arrays.copyOf(elements, len + 1);
//        newElements[len] = e;
//        setArray(newElements);
//        return true;
//    } finally {
//        lock.unlock();
//    }
//}
