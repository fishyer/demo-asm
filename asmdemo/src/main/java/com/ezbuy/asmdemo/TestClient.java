package com.ezbuy.asmdemo;

import com.ezbuy.asmdemo.aop.AopClassAdapter;
import com.ezbuy.asmdemo.aop.EnvConfig;
import com.ezbuy.asmdemo.aop.MyClassLoader;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestClient {

    @Test
    public void testFile() throws Exception {
        Person libai = new Person("libai", 24);
        String info = libai.getInfo();
        libai.say(info);
    }

    @Test
    public void testFile3() throws Exception {
        Class<?> src = Class.forName("com.ezbuy.asmdemo.Person");
        String path = src.getResource("").getPath() +src.getSimpleName() + ".class";
        System.out.println("path="+path);
        InputStream inputStream = new FileInputStream(path);
        //加入AOP
        ClassReader cr = new ClassReader(inputStream);
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new AopClassAdapter(Opcodes.ASM5, cw), ClassReader.EXPAND_FRAMES);
        byte[] data = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> personClass = myClassLoader.defineClass("com.ezbuy.asmdemo.Person", data);
        //反射调用构造方法
        Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
        Object libai = constructor.newInstance("libai", 24);
        //反射调用普通方法
        Method getInfo = personClass.getMethod("getInfo", null);
        Object info = getInfo.invoke(libai, null);
        Method say = personClass.getMethod("say", String.class);
        say.invoke(libai, info);
    }

    @Test
    public void testFile2() throws Exception {
        InputStream inputStream = new FileInputStream(EnvConfig.projectPath + "\\build\\classes\\java\\main\\com\\ezbuy\\asmdemo\\Person.class");
        //加入AOP
        ClassReader cr = new ClassReader(inputStream);
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new AopClassAdapter(Opcodes.ASM5, cw), ClassReader.EXPAND_FRAMES);
        byte[] data = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> personClass = myClassLoader.defineClass("com.ezbuy.asmdemo.Person", data);
        //反射调用构造方法
        Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
        Object libai = constructor.newInstance("libai", 24);
        //反射调用普通方法
        Method getInfo = personClass.getMethod("getInfo", null);
        Object info = getInfo.invoke(libai, null);
        Method say = personClass.getMethod("say", String.class);
        say.invoke(libai, info);
    }

    @Test
    public void testGen() throws Exception {
        //ASM创建Class
        byte[] data = PersonDump.dump();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> personClass = myClassLoader.defineClass("com.ezbuy.asmdemo.Person", data);
        //反射调用构造方法
        Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
        Object libai = constructor.newInstance("libai", 24);
        //反射调用普通方法
        Method getInfo = personClass.getMethod("getInfo", null);
        Object info = getInfo.invoke(libai, null);
        Method say = personClass.getMethod("say", String.class);
        say.invoke(libai, info);
    }

    @Test
    public void testGen2() throws Exception {
        //ASM创建Class
        byte[] data = PersonDump.dump();

        //加入AOP
        ClassWriter cw = new ClassWriter(0);
        ClassReader cr = new ClassReader(data);
        cr.accept(new AopClassAdapter(Opcodes.ASM5, cw), ClassReader.EXPAND_FRAMES);
        data = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> personClass = myClassLoader.defineClass("com.ezbuy.asmdemo.Person", data);
        //反射调用构造方法
        Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
        Object libai = constructor.newInstance("libai", 24);
        //反射调用普通方法
        Method getInfo = personClass.getMethod("getInfo", null);
        Object info = getInfo.invoke(libai, null);
        Method say = personClass.getMethod("say", String.class);
        say.invoke(libai, info);
    }


}
