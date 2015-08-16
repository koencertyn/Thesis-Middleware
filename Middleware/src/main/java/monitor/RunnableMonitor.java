package monitor;

import drool.instances.DroolInstance;

class RunnableMonitor implements Runnable {

	private Thread t;
	private String threadName;
	private MonitorTimePrediction pred;
	private final int TRESHOLD = 5;

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
		try {
			while (mayRun) {

				MonitorMeasurement newMeasurement = new MonitorMeasurement(
						MonitorMetaData.getSystemAVGLoad(),
						MonitorMetaData.getAvailableProcessors(),
						MonitorMetaData.getMemory());
				System.out.println("_______Start measurement_______");
				System.out.println(newMeasurement.getLoad());
				System.out.println(newMeasurement.getProcessor());
				System.out.println(newMeasurement.getMemory());
				System.out.println("_______End Measurement_______");
				DroolInstance newDroolInstance = new DroolInstance();
				System.out.println("generated random instance");
				newDroolInstance.evaluateMonitoring(newMeasurement);
				System.out.println(newMeasurement.hasDanger());
				if (newMeasurement.hasDanger()) {
					if (count < TRESHOLD){
						count++;
					} else {
						man.generateBackupInstance();
					}
					
				} else {
					if (count-- < 0){
						count = 0;
					} else {
						count--;
					}
				}
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