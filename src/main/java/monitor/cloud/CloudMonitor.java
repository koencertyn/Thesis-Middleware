package monitor.cloud;

import javax.inject.Inject;

import controller.cloud.CloudDisbander;
import entity.cloudInstance.CloudInstance;

public class CloudMonitor {
	
	@Inject
	private CloudDisbander disbander;
	
	public void disableCloud(CloudInstance instance){
		disbander.disbandCloud(instance.getPlatform(), instance);
	}
	
	public void startMonitoring(CloudInstance instance){
		RunnableCloudMonitor mon = new RunnableCloudMonitor(this,instance.getPlatform()+" -monitor", instance);
		mon.start();
	}
	
	
}
