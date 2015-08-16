package monitor;

import javax.ejb.Singleton;
import javax.inject.Inject;

import entity.cloudInstance.CloudInstance;
import entity.cloudInstance.CloudInstanceRegistration;

@Singleton
public class MonitorController {
	
	private static MonitoringThread thread = null;
	
	@Inject
	private CloudInstanceRegistration ci;

	public void startMonitoring(){
		if(thread == null){
			thread = new MonitoringThread();
			thread.startThread(this);
		} else {
			System.out.println("thread is already running.");
		}
	}

	public void generateBackupInstance(){
		CloudInstance in = new CloudInstance();
		in.setPlatform("hallo dit is een 2");
    	try {
			ci.register(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("booting backup");
	}	
	

}
