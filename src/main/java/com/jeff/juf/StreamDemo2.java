package com.jeff.juf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流(Stream) 到底是什麼呢?
 * 式數據渠道，用於操作數據源(集合,數組等) 所生成的元素序列
 * 集合講的是數據，流講的是計算
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().filter(t -> {
            return t.getId() % 2 == 0;
        }).filter(t -> {
            return t.getAge() > 24;
        }).map(m -> {
            return m.getUserName().toUpperCase();
        }).sorted((t1, t2) -> {
            return t2.compareTo(t1);
        }).limit(1).forEach(System.out::println);

    }
}

