import com.sun.tools.attach.VirtualMachine;

/**
 * 打包后MANIFEST.MF内容
Agent-Class: agent.TestAgent
Premain-Class: agent.TestAgent
Can-Redine-Classes: true
Can-Retransform-Classes: true
 */
public class loadDynamicAgent {

	/**
	 * 解决VirtualMachine类找不到的问题
	 * https://blog.csdn.net/mcb520wf/article/details/84958339
	 * 先跑DynamicAgentTestMain，然后经常号填DynamicAgentTestMain的进程即可
	 */
	public static void main(String[] args) throws Exception {
		VirtualMachine vm = null;
		String agentjarpath = "D:\\Users\\ben01.li\\Desktop\\viplearning\\jvm-sandbox\\lhm-test\\target\\lhm-test-1.2.0.jar";
		vm = VirtualMachine.attach("23824");
		vm.loadAgent(agentjarpath, "This is Args to the Agent.");
		vm.detach();
	}

}
