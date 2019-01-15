package com.shanhy.demo.asm.hello;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import asm.com.shanhy.demo.asm.hello.PersonDump;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class HelloTest {

    @Test
    public void testFile() throws Exception {
        Hello2 hello2 = new Hello2();
        hello2.display();
        List<String> result = hello2.testList();
        System.out.println(result);
    }

    @Test
    public void testGen() throws Exception {
        byte[] data = HelloGeneratorClass.generatorHelloClass();

//        ClassWriter cw = new ClassWriter(0);
//        ClassReader cr = new ClassReader(data);
//        cr.accept(new AopClassAdapter(Opcodes.ASM5, cw), ClassReader.SKIP_DEBUG);
//        data = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> helloClass = myClassLoader.defineClass("com.shanhy.demo.asm.hello.Hello", data);
        Object obj = helloClass.newInstance();
        Method method = helloClass.getMethod("display", null);
        method.invoke(obj, null);

        method = helloClass.getMethod("testList", null);
        Object result = method.invoke(obj, null);
        System.out.println(result);
    }

    @Test
    public void testGen2() throws Exception {
        byte[] data = HelloDump.dump();

//        ClassWriter cw = new ClassWriter(0);
//        ClassReader cr = new ClassReader(data);
//        cr.accept(new AopClassAdapter(Opcodes.ASM5, cw), ClassReader.SKIP_DEBUG);
//        data = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> helloClass = myClassLoader.defineClass("com.shanhy.demo.asm.hello.Hello", data);
        Object obj = helloClass.newInstance();
        Method method = helloClass.getMethod("display", null);
        method.invoke(obj, null);

        method = helloClass.getMethod("testList", null);
        Object result = method.invoke(obj, null);
        System.out.println(result);
    }

    @Test
    public void testGen3() throws Exception {
        byte[] data = PersonDump.dump();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> helloClass = myClassLoader.defineClass("com.shanhy.demo.asm.hello.Hello", data);
    }
}
