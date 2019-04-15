package asm.MethodVisitor;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class LoadSpyAgent {

    public static void premain(String args, Instrumentation inst) throws Exception {
        System.out.println("-----------load spy begin -----------");
        inst.appendToBootstrapClassLoaderSearch(new JarFile(new File("C:\\Users\\Administrator\\Desktop\\jvm-sandbox\\sandbox-spy\\target\\sandbox-spy-1.2.0.jar")));
        System.out.println("-----------load spy end -----------");
    }

}
