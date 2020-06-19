package com.jeff.juc;

public class Test {
    public static void main(String[] args) {
        minDays(new int[]{7,7,7,7,12,7,7}, 2 ,3);
    }

    public static int minDays(int[] bloomDay, int m, int k) {

        if(m * k > bloomDay.length){
            return -1;
        }

        int right = (int) 1e9;
        int left = 1;
        int kk = 0;
        int mm = 0;

        while(left < right){
            kk = 0;
            mm = 0;
            int mid = (left + right) / 2;
            for(int day : bloomDay) {
                if(day > mid){
                    kk = 0;
                }else {
                    kk++;
                    if(kk == k){
                        mm++;
                        kk = 0;
                    }

                }
            }

            if(mm < m){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        return left;
    }
}
