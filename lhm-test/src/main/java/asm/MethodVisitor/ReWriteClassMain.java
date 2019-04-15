package asm.MethodVisitor;

import com.alibaba.jvm.sandbox.api.event.Event;
import com.alibaba.jvm.sandbox.core.enhance.weaver.asm.EventWeaver;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

/**
 * http://web.cs.ucla.edu/~msb/cs239-tutorial/
 */
public class ReWriteClassMain {

    /**
     * 1.先执行mvn assembly:assembly
     * 2.修改MANIFEST.MF加入
     * Premain-Class: asm.MethodVisitor.LoadSpyAgent
     * Can-Redine-Classes: true
     * Can-Retransform-Classes: true
     * <p>
     * 3.ide中启动程序，vm options：
     * -javaagent:D:\Users\ben01.li\Desktop\viplearning\jvm-sandbox\lhm-test\target\lhm-test-1.2.0.jar
     * <p>
     * 调动的会是LoadSpyAgent的premain
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        myFirstClassWriete();

        ClassReader classReader = new ClassReader(TestMethodVisitorToBeTest.class.getName());
        Event.Type[] eventTypeArray = Event.Type.values();
        ClassWriter classWriter = new ClassWriter(COMPUTE_MAXS);
        HashSet<String> signCodes = new HashSet<String>();
        signCodes.add("TestMethodVisitorToBeTest#<init>()");
        signCodes.add("asm.MethodVisitor.TestMethodVisitorToBeTest#main(java.lang.String[])");
        EventWeaver eventWeaver = new EventWeaver(Opcodes.ASM7, classWriter,
                "default", 1001, 0,
                classReader.getClassName(), signCodes, eventTypeArray);

        classReader.accept(eventWeaver, 0);
        classWriter.visitEnd();
        byte[] bytes = classWriter.toByteArray();
        File file = new File("C:\\Users\\Administrator\\Desktop\\jvm-sandbox\\lhm-test\\src\\main\\java\\asm\\MethodVisitor\\" + TestMethodVisitorToBeTest.class.getSimpleName() + ".class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        System.out.println("all end");
    }

    private static void myFirstClassWriete() throws IOException {
        ClassReader classReader = new ClassReader(TestMethodVisitorToBeTest.class.getName());
        ClassWriter classWriter = new ClassWriter(COMPUTE_MAXS);
        classReader.accept(new ReWriteClassMain().new ClassAdapter(classWriter), 0);

        classWriter.visitEnd();
        byte[] bytes = classWriter.toByteArray();
        File file = new File("C:\\Users\\Administrator\\Desktop\\jvm-sandbox\\lhm-test\\src\\main\\java\\asm\\MethodVisitor\\" + TestMethodVisitorToBeTest.class.getSimpleName() + ".class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        System.out.println("all end");
    }

    class ClassAdapter extends ClassVisitor implements Opcodes {

        public ClassAdapter(final ClassVisitor cv) {
            super(ASM7, cv);
        }

        @Override
        public MethodVisitor visitMethod(final int access, final String name,
                                         final String desc, final String signature, final String[] exceptions) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            return mv == null ? null : new MethodAdapter(mv);
        }
    }

    class MethodAdapter extends MethodVisitor implements Opcodes {

        public MethodAdapter(final MethodVisitor mv) {
            super(ASM7, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            /* TODO: System.err.println("CALL" + name); */

            /* do call */
            mv.visitMethodInsn(opcode, owner, name, desc, itf);

            /**
             * 通过写java源代码，然后在ASM plugin（ASMifier）中查看就能知道下面要这么写了。
             * 相当于System.err.println("RETURN printOne");
             * descriptor就是类型的意思，比如int就是I，对象用L开头表示
             */
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("RETURN printOne");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            /* TODO: System.err.println("RETURN" + name);  */
        }
    }

}
