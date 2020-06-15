package com.jeff.juc;

import java.util.concurrent.TimeUnit;

/**
 * 8 lock
 * 1.標準訪問，請問先打印郵件還是短信 => 郵件
 * 2.暫停四秒鐘再郵件方法，請問先打印郵件還是短信 => 郵件
 * 3.新增普通sayHello方法，請問先打印郵件還是hello => hello
 * 4.兩部手機，請問先打印郵件還是短信 => 短信
 * 5.兩個靜態同步方法，同一部手機，請問先打印郵件還是短信 => 郵件
 * 6.兩個靜態同步方法，兩部手機，請問先打印郵件還是短信 => 郵件
 * 7.一個靜態同步方法，一個普通同步方法，同一部手機，請問先打印郵件還是短信 => 短信
 * 8.一個靜態同步方法，一個普通同步方法，兩部手機，請問先打印郵件還是短信 => 短信
 *
 * 筆記:
 *  一個對象裡面如果有多個synchronized方法，某一個時刻內，只有一個線程去掉用其中的一個sychronized方法了，
 *  其他的線程都只能等待，換句話說，某一個時刻內，只能有唯一一個線程去訪問這些synchronized方法
 *
 *  鎖的是當前對象this，被鎖定後，其他的現成都不能進入到當前對象的其他synchronized方法
 *
 *  加個普通方法後發現和同步鎖無關
 *  換成兩個對象後，不是同一把鎖了，情況立刻變化
 *
 *  synchronized實現同步的基礎: java中的每一個對象都可以作為鎖。
 *  具體表現為以下三種型式
 *  對於普通同步方法，鎖是當前實例對象
 *  對於同步方法塊，鎖示Synchronized括號裡配置的對象
 *  對於靜態同步方法，鎖是當前類的class對象
 *
 *  當一個線程試圖訪問同步代碼塊時，他首先必須得到鎖，退出或拋出異常時必須釋放鎖。
 *  也就是說如果一個實例對象的非靜態同步方法獲取鎖後，該實例對象的其他非靜態同步方法必須等待獲取所的方法釋放鎖後才能獲得鎖
 *  所以無需等待該實例對象已獲的鎖的非靜態同步方法釋放鎖就可以獲取他們自己的鎖
 *
 *  所有的靜態同步方法用的也是同一把鎖=>類對象本身。
 *  這兩把鎖是兩個不同的對象，所以靜態同步方法與非靜態同步方法之間是不會有竟爭條件
 *  但是一旦一個靜態同步方法獲取鎖後，其他的靜態同步法都必須等待該方法釋放鎖後才能獲取鎖
 *  而不管是同一個實例對象的靜態同不方法之間
 *  還是不同的實例對象的靜態同步方法之間，只要他們是同一個類的實例對象
 */
public class Lock8Demo04 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendMS();
//                phone.sayHello();
//                phone2.sendMS();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        },"B").start();

    }
}

class Phone {

    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail");
    }

    public static synchronized void sendMS() throws Exception {
        System.out.println("sendMS");
    }

    public void sayHello() throws Exception {
        System.out.println("say hello");
    }

}
