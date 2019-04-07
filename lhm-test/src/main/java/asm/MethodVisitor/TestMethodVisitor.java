package asm.MethodVisitor;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TestMethodVisitor extends MethodVisitor {

    private String methodName;

    public TestMethodVisitor(MethodVisitor methodVisitor, String methodName) {
        super(Opcodes.ASM4, methodVisitor);
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        System.out.println("at Method ‘" + methodName + "’ Begin...");
        super.visitCode();
    }

    @Override
    public void visitEnd() {
        System.out.println("at Method ‘" + methodName + "’End.");
        super.visitEnd();
    }
}
