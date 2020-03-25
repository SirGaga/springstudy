package com.zhangjie.exam;

public class StringTest {

    public static void main(String[] args) {

        // 只要使用了new关键字，java就会创建一个新的对象，而且这个对象是在堆里边


        // 执行完这一句之后Java会在内存中会创建两个对象，而且他们的内容都是abc，
        // 首先在String Pool里边去查找有没有一个内容为abc的对象存在，若没有，则把abc作为一个对象放入String Pool中
        // 紧接着在堆（Heap）创建new String("abc")这个对象，s作为对象的引用指向new String("abc")这个堆里边的对象
        String s = new String("abc");
        // 这句话执行完，内存中一个对象也没有生成，首先java会去String Pool中查找内容是abc的字符串对象存在
        // 若存在，s1则最为对象的引用指向String Pool中内容是abc的字符串对象
        String s1 = "abc";
        // 这句执行完，首先java会去String Pool中查找内容是abc的字符串对象存在
        // 若存在，紧接着在堆（Heap）创建new String("abc")这个对象，
        // s2作为对象的引用指向这个刚创建的new String("abc")这个堆里边的对象
        String s2 = new String("abc");
        // 在Java中，== 永远比较的是两个对象的内存地址也可以说是比较等号两边的引用是否指向同一个对象
        // 对于java中的8个原生数据类型，比较的是值；对于引用类型来说判断的是引用的地址是不是一样
        System.out.println(s == s1);

        System.out.println(s == s2);

        System.out.println(s1 == s2);

        // 下面三个的具体结果看String类的intern()
        System.out.println(s == s.intern());// false

        System.out.println(s1 == s1.intern());// true

        System.out.println(s1.intern() == s2.intern());//true

        String hello = "hello";
        String hel = "hel";
        String lo = "lo";

        // +号两边都是字面值即常量时，返回的就是对String Pool中的"hello"这个字符串的引用
        System.out.println(hello == "hel"+"lo");// true，
        // lo是一个对象的引用，当他跟字符串拼接的时候，
        // 返回的是在堆（Heap）创建的并且是String Pool中的"hello"这个字符串的copy的引用，所以false
        System.out.println(hello == "hel" + lo);// false，

    }

}
