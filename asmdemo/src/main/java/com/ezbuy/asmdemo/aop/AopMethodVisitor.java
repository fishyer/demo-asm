package com.ezbuy.asmdemo.aop;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class AopMethodVisitor extends MethodVisitor implements Opcodes {

    public AopMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "com/ezbuy/asmdemo/aop/AopInteceptor", "before", "()V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        // 在返回之前安插after 代码。
        if (opcode >= IRETURN && opcode <= RETURN) {
            this.visitMethodInsn(INVOKESTATIC, "com/ezbuy/asmdemo/aop/AopInteceptor", "after", "()V", false);
        }
        super.visitInsn(opcode);
    }

}
