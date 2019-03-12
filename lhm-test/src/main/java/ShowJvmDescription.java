import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

public class ShowJvmDescription {

	/**
	 * 列出所有jvm描述实例
	 */
	public static void main(String[] args) {
		List<VirtualMachineDescriptor> virtualMachineDescriptors = VirtualMachine.list();
		for (VirtualMachineDescriptor virtualMachineDescriptor : virtualMachineDescriptors) {
			System.out.println("pid = " + virtualMachineDescriptor.id() + ":" + virtualMachineDescriptor.displayName());
		}
	}

}


