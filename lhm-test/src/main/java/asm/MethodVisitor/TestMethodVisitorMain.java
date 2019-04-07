package asm.MethodVisitor;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * https://my.oschina.net/ta8210/blog/220011
 */
public class TestMethodVisitorMain {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader(TestMethodVisitorToBeTest.class.getName());
        classReader.accept(new TestClassVisitor(), ClassReader.SKIP_DEBUG);
        System.out.println("all end");
    }

}
