package com.jeff.juc;

import com.jeff.juc.entities.Person;

public class TestTransferValue {

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println(age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName = "+person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("string = "+str);
    }

    public void changeValue1(int age) {
        age = 30;
    }
    public void changeValue2(Person person) {
        person.setPersonName("test");
    }
    public void changeValue3(String str) {
        str = "xxx";
    }
}
