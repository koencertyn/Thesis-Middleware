package monitor;

import entity.request.RequestRepository;

public class MonitoringThread {
	
	private boolean running = false;
	
	public void startThread(MonitorController man, RequestRepository repo){
		if(!running){
			RunnableMonitor R1 = new RunnableMonitor("Monitoringthread",man,repo);
		    R1.start();
		    running = true;
	}
	
}}
