package asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

public class HelloWorld {

    public static void main(String args[]) throws Exception {
        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/lhm/asm/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null,
                "java/lang/Object", null);


        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
                "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello",
                "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        File file = new File("C:\\Users\\Administrator\\Desktop\\jvm-sandbox\\lhm-test\\src\\main\\java\\asm\\HelloWorld.class");
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();
    }

}