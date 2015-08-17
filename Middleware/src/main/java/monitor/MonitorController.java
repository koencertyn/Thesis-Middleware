package monitor;

import javax.ejb.Singleton;
import javax.inject.Inject;

import controller.cloud.CloudManager;
@Singleton
public class MonitorController {
	
	private static MonitoringThread thread = null;
	
	@Inject
	private CloudManager manager;

	public void startMonitoring(){
		if(thread == null){
			thread = new MonitoringThread();
			thread.startThread(this);
		} else {
			System.out.println("thread is already running.");
		}
	}

	public void generateBackupInstance(){
		System.out.println("booting backup");
		manager.bootBackupCloud();
	}	
	

}
