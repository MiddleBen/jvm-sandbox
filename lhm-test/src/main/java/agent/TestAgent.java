package agent;

import java.lang.instrument.Instrumentation;

/**
 * 打包后MANIFEST.MF内容
 Agent-Class: agent.TestAgent
 Premain-Class: agent.TestAgent
 Can-Redine-Classes: true
 Can-Retransform-Classes: true
 */
public class TestAgent {

	/**
	 * 下面两个方法的名字应该是约定的，一定要这么命名
	 */
	public static void agentmain(String args, Instrumentation inst) throws Exception {
		System.out.println("agentmain Args:" + args);
	}

	public static void premain(String args, Instrumentation inst) throws Exception {
		System.out.println("premain Args:" + args);
		Class[] classes = inst.getAllLoadedClasses();
		for (Class clazz : classes) {
			System.out.println(clazz.getName());
		}
	}
}
