import java.lang.management.ManagementFactory;
import java.util.Arrays;

import com.sun.management.OperatingSystemMXBean;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.util.FormatUtil;
import oshi.util.Util;

public class Main {

    public static void main (String[] args) {
        int a = 10;
        double b = 1.5;
        System.out.println((int)(a * b));
    }

    private static void printCpu(CentralProcessor processor) {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Thread t = new Thread();
        System.out.println(bean.getProcessCpuLoad());
        System.out.println(bean.getSystemCpuLoad());
        System.out.println(bean.getAvailableProcessors());
        System.out.println(bean.getFreePhysicalMemorySize());
        System.out.println(bean.getProcessCpuTime());
        OSProcess p = new OSProcess();
        System.out.println(p.getOpenFiles());
        System.out.println(p.getName());
    }

}