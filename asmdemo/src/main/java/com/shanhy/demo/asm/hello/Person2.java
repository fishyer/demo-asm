package com.shanhy.demo.asm.hello;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class Person2 {

    private String name;
    private int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say(String desc) {
        System.out.println(String.format("Hello,%s", desc));
    }

    public String getInfo() {
        return "name=" + name + ",age=" + age;
    }
}
