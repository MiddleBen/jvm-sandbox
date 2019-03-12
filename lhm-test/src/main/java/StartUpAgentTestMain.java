/**
 * 启动时指定agent
 */
public class StartUpAgentTestMain {

	/**
	 * 启动vm options：
	 * -javaagent:D:\Users\ben01.li\Desktop\viplearning\jvm-sandbox\lhm-test\target\lhm-test-1.2.0.jar
	 *
	 * 调动的会是StartUpAgent的premain
	 */
	public static void main(String[] args) {
		System.out.println("StartUpAgentTestMain done");
	}

}
