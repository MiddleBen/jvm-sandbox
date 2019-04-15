//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package asm.MethodVisitor;

import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.core.enhance.weaver.asm.AsmMethods;

import java.com.alibaba.jvm.sandbox.spy.Spy;
import java.com.alibaba.jvm.sandbox.spy.Spy.Ret;

/**
 * TestMethodVisitorToBeTestFrom经过EventWeaver改写后的源码
 */
public class TestMethodVisitorToBeTestFromSpy implements Module, AsmMethods {
    public TestMethodVisitorToBeTestFromSpy() {
    }

    public static void main(String[] var0) throws Throwable {
        boolean var10000 = true;
        Object[] var2 = new Object[]{var0};
        Ret var10002 = Spy.spyMethodOnBefore(var2, "default", 1001, 0, "asm.MethodVisitor.TestMethodVisitorToBeTest", "main", "([Ljava/lang/String;)V", (Object) null);
        var0 = (String[]) var2[0];
        int var10001 = var10002.state;
        Object var6;
        if (var10001 == 1) {
            var6 = var10002.respond;
        } else if (var10001 != 2) {
            try {
                var10000 = true;
                System.out.println("this is TestMethodVisitorToBeTest");
                var10000 = true;
                Ret var3 = Spy.spyMethodOnReturn((Object) null, "default", 1001);
                var10001 = var3.state;
                if (var10001 != 1) {
                    if (var10001 != 2) {
                        var10000 = true;
                    } else {
                        throw (Throwable) var3.respond;
                    }
                } else {
                    var6 = var3.respond;
                }
            } catch (Throwable var1) {
                boolean var4 = true;
                Ret var5 = Spy.spyMethodOnThrows(var1, "default", 1001);
                int var8 = var5.state;
                if (var8 != 1) {
                    if (var8 != 2) {
                        var4 = true;
                        throw var1;
                    } else {
                        throw (Throwable) var5.respond;
                    }
                } else {
                    Object var7 = var5.respond;
                }
            }
        } else {
            throw (Throwable) var10002.respond;
        }
    }
}
