package asm.MethodVisitor;

import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.core.enhance.weaver.asm.AsmMethods;

public class TestMethodVisitorToBeTest implements Module, AsmMethods {

    public static void main(String[] args) {
        System.out.println("this is TestMethodVisitorToBeTest");
    }

}
