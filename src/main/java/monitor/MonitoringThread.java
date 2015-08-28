package monitor;

public class MonitoringThread {
	
	private boolean running = false;
	
	public void startThread(MonitorController man){
		if(!running){
			RunnableMonitor R1 = new RunnableMonitor("Monitoringthread",man);
		    R1.start();
		    running = true;
	}
	
}}
