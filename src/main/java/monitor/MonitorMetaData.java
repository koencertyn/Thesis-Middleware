package monitor;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;

import javax.management.MBeanServerConnection;

public class MonitorMetaData {

	static final int MegaBytes = 10241024;

	public static double getSystemAVGLoad() {
		if(isEvenTime())
			return getSafeLoad();
		else return getDangerousLoad();
		/*
		MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
	
		OperatingSystemMXBean osMBean;
		try {
			osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc,
					ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
					OperatingSystemMXBean.class);
			double cpuBefore = osMBean.getSystemLoadAverage();
			return cpuBefore;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;
		}
		*/
	}

	public static double getAvailableProcessors() {
		/*MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
	
		OperatingSystemMXBean osMBean;
		try {
			osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc,
					ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
					OperatingSystemMXBean.class);
			double cpuBefore = osMBean.getAvailableProcessors();
			return cpuBefore;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;
		}*/
		return getSafeProc();
	}

	public static double getMemory() {
		/*
		long freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;
		
		// long totalMemory = Runtime.getRuntime().totalMemory()/MegaBytes;
		// long maxMemory = Runtime.getRuntime().maxMemory()/MegaBytes;
	
		freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;
		// totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
		// maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
	
		return freeMemory;
		*/
		return getSafeMemory();
	
	}
	
	public static double getSafeMemory(){
		return 100;
	}
	
	public static double getDangerousMemory(){
		return 10;
	}
	
	public static double getSafeLoad(){
		return 0;
	}
	
	public static double getSafeProc(){
		return 20;
	}

	@SuppressWarnings("deprecation")
	public static boolean isEvenTime(){
		Date d = new Date();
		return (d.getMinutes() % 2 == 0) ;
	}
	
	private static double getDangerousLoad() {
		return 4;
	}

}
