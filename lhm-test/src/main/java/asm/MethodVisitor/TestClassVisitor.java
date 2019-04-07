package asm.MethodVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class TestClassVisitor extends ClassVisitor {

    public TestClassVisitor() {
        super(Opcodes.ASM4);
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("at Method " + name);
        MethodVisitor superMV = super.visitMethod(access, name, desc, signature, exceptions);
        return new TestMethodVisitor(superMV, name);
    }
}