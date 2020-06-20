package com.jeff.juf;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        // 函數式接口
        Function<String, Integer> function = s -> {return s.length();};

        System.out.println(function.apply("abc"));

//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        };
        // 斷定型接口
        Predicate<String> predicate = s -> {return s.isEmpty();};

        System.out.println(predicate.test("test"));

//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//
//            }
//        };
        // 消費型接口
        Consumer<String> consumer = s -> {System.out.println(s);};

        consumer.accept("test");

//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "test";
//            }
//        };
        // 供給型接口
        Supplier<String> supplier = () -> {return "test";};
        System.out.println(supplier);
    }
}

class User {
    private Integer id;
    private String userName;
    private Integer age;

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

interface MyIntegerface {
    public int myInt(int x);
    public String myString(String str);
    public boolean isOk(String s);
}
