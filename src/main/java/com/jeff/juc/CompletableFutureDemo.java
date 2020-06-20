package com.jeff.juc;


import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception
    {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 沒有返回, update mysql ok");
        });
        completableFuture.get();

        CompletableFuture integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回, insert sql ok");
            return 1024;
        });

        integerCompletableFuture.whenComplete((t,u) -> {
            System.out.println("t = "+t);
            System.out.println("u = "+u);
        }).exceptionally(f -> {
            System.out.println("excptionL"+f);
            return 444;
        }).get();

    }
}
