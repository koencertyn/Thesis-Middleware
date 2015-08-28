package monitor;

import drool.instances.DroolInstance;

class RunnableMonitor implements Runnable {

	private Thread t;
	private String threadName;
	private MonitorTimePrediction pred;
	private final int TRESHOLD = 1;
	
	private final int timer = 1;

	private MonitorController man;

	// We do this because of the deprecation of the stopthread method.
	private boolean mayRun = true;

	RunnableMonitor(String name, MonitorController man) {
		this.man = man;
		pred = new MonitorTimePrediction();
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	@Override
	public void run() {
		System.out.println("Running " + threadName);
		double avgLoad = MonitorMetaData.getSystemAVGLoad();
		double avProc = MonitorMetaData.getAvailableProcessors();
		double mem = MonitorMetaData.getMemory();
		int sleepTime = 5000;
		int count = 0;
		int t = 0;
		int idle = 0;
		boolean bootedBackup = false;
		try {
			while (mayRun) {
				System.out.println("--- RUNNING PRIVATE MONITOR MEASUREMENT ----");
				MonitorMeasurement newMeasurement;
				if(t < timer){
					newMeasurement = new MonitorMeasurement(MonitorMetaData.getSafeLoad(),MonitorMetaData.getSafeProc(),MonitorMetaData.getSafeMemory());
				} else {
					newMeasurement = new MonitorMeasurement(MonitorMetaData.getSafeLoad(),MonitorMetaData.getSafeProc(),MonitorMetaData.getDangerousMemory());
				}
				
				DroolInstance newDroolInstance = new DroolInstance();
				System.out.println("checked status private cloud");
				newDroolInstance.evaluateMonitoring(newMeasurement);
				System.out.println(newMeasurement.hasDanger());
				if (newMeasurement.hasDanger()) {
					if (count < TRESHOLD){
						count++;
					} else {
						if(! bootedBackup){
							bootedBackup = true;
							man.generateBackupInstance();
						} else {
							if(idle > 100000000)
								bootedBackup = false;
						}
						idle++;
						count = 0;
					}
					
				} else {
					if (count-- < 0){
						count = 0;
					} else {
						count--;
					}
				}
				t++;
				Thread.sleep(pred.calcNewSleepTime(avgLoad, avProc, mem,
						MonitorMetaData.getSystemAVGLoad(),
						MonitorMetaData.getAvailableProcessors(),
						MonitorMetaData.getMemory(), sleepTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void stop() {
		System.out.println("Stopping " + threadName);
		if (t != null) {
			this.mayRun = false;
		}
	}

}