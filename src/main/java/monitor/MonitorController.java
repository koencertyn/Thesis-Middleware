package monitor;

import javax.ejb.Singleton;
import javax.inject.Inject;

import resources.Configs;
import controller.cloud.CloudManager;
import entity.request.RequestRepository;
@Singleton
public class MonitorController {
	
	private static MonitoringThread thread = null;
	
	@Inject
	private CloudManager manager;
	
	@Inject
	private RequestRepository reqRepo;
	
	public void startMonitoring(){
		if(thread == null){
			thread = new MonitoringThread();
			thread.startThread(this,reqRepo);
		} else {
			System.out.println("thread is already running.");
		}
	}

	public void generateBackupInstance(){
		System.out.println("booting backup");
		manager.bootBackupCloud();
	}	
	

}
