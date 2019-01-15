package com.shanhy.demo.asm.hello;

import java.util.*;

import org.objectweb.asm.*;

public class HelloDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "com/shanhy/demo/asm/hello/Hello", null, "java/lang/Object", null);

        cw.visitSource("Hello.java", null);

        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "FLAG", "Ljava/lang/String;", null, "my final constant");
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(13, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lcom/shanhy/demo/asm/hello/Hello;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "display", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(20, l0);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{Opcodes.INTEGER}, 0, null);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitIntInsn(BIPUSH, 8);
            Label l2 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l2);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(21, l3);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn(">>>>>>>>>>my final constant");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(20, l4);
            mv.visitIincInsn(1, 1);
            mv.visitJumpInsn(GOTO, l1);
            mv.visitLabel(l2);
            mv.visitLineNumber(23, l2);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitInsn(RETURN);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLocalVariable("i", "I", null, l1, l2, 1);
            mv.visitLocalVariable("this", "Lcom/shanhy/demo/asm/hello/Hello;", null, l0, l5, 0);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "testList", "()Ljava/util/List;", "()Ljava/util/List<Ljava/lang/String;>;", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(27, l0);
            mv.visitTypeInsn(NEW, "java/util/ArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(28, l1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitLdcInsn("Tome");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(29, l2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitLdcInsn("Jack");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(30, l3);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitLdcInsn("Lily");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(31, l4);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn(">>>>>>>>>>testList > list.size = ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "size", "()I", true);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(32, l5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(ARETURN);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLocalVariable("this", "Lcom/shanhy/demo/asm/hello/Hello;", null, l0, l6, 0);
            mv.visitLocalVariable("list", "Ljava/util/List;", "Ljava/util/List<Ljava/lang/String;>;", l1, l6, 1);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "testMapList", "(Z[Ljava/util/Map;)Ljava/util/List;", "(Z[Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(38, l0);
            mv.visitTypeInsn(NEW, "java/util/ArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 3);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(39, l1);
            mv.visitVarInsn(ILOAD, 1);
            Label l2 = new Label();
            mv.visitJumpInsn(IFEQ, l2);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(40, l3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 6);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"com/shanhy/demo/asm/hello/Hello", Opcodes.INTEGER, "[Ljava/util/Map;", "java/util/List", "[Ljava/util/Map;", Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[]{});
            mv.visitVarInsn(ILOAD, 6);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitJumpInsn(IF_ICMPGE, l2);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 7);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(41, l5);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(40, l6);
            mv.visitIincInsn(6, 1);
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitLineNumber(44, l2);
            mv.visitFrame(Opcodes.F_CHOP, 3, null, 0, null);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn(">>>>>>>>>>testMapList > list.size = ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "size", "()I", true);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(45, l7);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLocalVariable("m", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", l5, l6, 7);
            mv.visitLocalVariable("this", "Lcom/shanhy/demo/asm/hello/Hello;", null, l0, l8, 0);
            mv.visitLocalVariable("val", "Z", null, l0, l8, 1);
            mv.visitLocalVariable("map", "[Ljava/util/Map;", "[Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", l0, l8, 2);
            mv.visitLocalVariable("list", "Ljava/util/List;", "Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;", l1, l8, 3);
            mv.visitMaxs(3, 8);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
