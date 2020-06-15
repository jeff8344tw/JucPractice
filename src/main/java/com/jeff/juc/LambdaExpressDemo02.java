package com.jeff.juc;

/**
 * 1. 函數示編程
 * 2. @FuctionalInterface
 * 3. default
 * 4. static
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello");
//            }
//
//            @Override
//            public int add(int x, int y) {
//                return 0;
//            }
//        };
//        foo.sayHello();

//        Foo foo = () -> {System.out.println("hello"); };
//        foo.sayHello();

        Foo foo = (int x,int y) -> {
            System.out.println("add");
            return x + y;
        };
        foo.add(3, 5);

        foo.mul(3, 5);

        Foo.div(10, 2);
    }
}

@FunctionalInterface
interface Foo{
//    void sayHello();

    int add(int x, int y);

    default int mul(int x, int y){
        return x * y;
    }

    static int div(int x, int y){
        return x / y;
    }
}
