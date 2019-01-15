package com.ezbuy.asmdemo;

import com.shanhy.demo.asm.hello.AopClassAdapter;
import com.shanhy.demo.asm.hello.HelloGeneratorClass;
import com.shanhy.demo.asm.hello.MyClassLoader;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestClient extends ClassLoader {

    @Test
    public void testFile() throws Exception {
        Person2 libai = new Person2("libai", 24);
        String info = libai.getInfo();
        libai.say(info);
    }

    @Test
    public void testGen() throws Exception {
        //ASM创建Class
        byte[] data = PersonDump.dump();

        ClassWriter cw = new ClassWriter(0);
        ClassReader cr = new ClassReader(data);
        cr.accept(new AopClassAdapter(Opcodes.ASM5, cw), ClassReader.SKIP_DEBUG);

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
