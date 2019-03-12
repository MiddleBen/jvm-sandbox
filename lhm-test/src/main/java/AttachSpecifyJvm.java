import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class AttachSpecifyJvm {

	public static void main(String[] args) throws IOException, AttachNotSupportedException {
		//Attach到JVM上
		VirtualMachine virtualmachine = VirtualMachine.attach("23824");
		//加载Agent
		String javaHome = virtualmachine.getSystemProperties().getProperty("java.home");
//		String agentPath =
//				javaHome + File.separator + "jre" + File.separator + "lib" + File.separator + "management-agent.jar";
//		String agentPath = "D:\\Users\\ben01.li\\Desktop\\viplearning\\jvm-sandbox\\lhm-test\\target\\lhm-test-1.2.0.jar";
		String agentPath = "D:\\Users\\ben01.li\\Desktop\\sandbox-agent.jar";
		File file = new File(agentPath);
		if (!file.exists()) {
			agentPath = javaHome + File.separator + "lib" + File.separator + "management-agent.jar";
			file = new File(agentPath);
			if (!file.exists()) {
				throw new IOException("Management agent not found");
			}
		}
		agentPath = file.getCanonicalPath();
		try {
			virtualmachine.loadAgent(agentPath, "com.sun.management.jmxremote");
		} catch (AgentLoadException e) {
			throw new IOException(e);
		} catch (AgentInitializationException agentinitializationexception) {
			throw new IOException(agentinitializationexception);
		}
		Properties properties = virtualmachine.getAgentProperties();
		String address = (String) properties.get("com.sun.management.jmxremote.localConnectorAddress");
		System.out.println("address = " + address);
		virtualmachine.detach();
	}
}
