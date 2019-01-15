package com.ezbuy.runtime;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class GenerateClass {

    public static void main(String args[]) {
        ClassWriter classWriter = new ClassWriter(0);
        // 创建类的头 public interface Comparable extends Mesurable
        // param 1: Java 版本
        // param 2: public abstract interface
        // param 3: 全路径类名
        // param 4: 泛型
        // param 5: 父类，接口隐式继承自Object
        // param 6: 接口数组
        classWriter.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/asm/createclass/Comparable", null, "java/lang/Object", new String[]{"com/asm/createclass/Mesurable"});
        // 创建 int LESS = -1;
        // param 1: public static final
        // param 2: 字段名称
        // param 3: 字段类型
        // param 4: 泛型
        // param 5: 字段的值
        // 由于这里没有注释，我们立即调用返回FieldVisitor的visitEnd方法，没有调用visitAnnotation或visitAttribute方法。
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "LESS", "I", null, new Integer(-1)).visitEnd();
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "EQUAL", "I", null, new Integer(0)).visitEnd();
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "GREATER", "I", null, new Integer(1)).visitEnd();
        classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Onbject;)I", null, null).visitEnd();
        classWriter.visitEnd();
        byte[] data = classWriter.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(new File("Comparable.class"));
            fos.write(data);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 使用创建好的class文件
        Class clazz = new MyClassLoader().defineClass("com.asm.createclass.Comparable", data);
        try {
            Field field = clazz.getField("LESS");
            Integer o = (Integer) field.get(null);
            System.out.println(o.intValue()); // 输出-1

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] bytes) {
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
