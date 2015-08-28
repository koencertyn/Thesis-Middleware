package monitor;

import resources.Configs;

public class MonitorTimePrediction {

	private double predictionValue = Configs.PREDICTIONVALUE;

	public int calcNewSleepTime(double avgLoad, double avProc, double mem,
			double avgLoadNew, double avProcNew, double memNew, int sleepTime) {
		if (avgLoad * predictionValue < avgLoadNew) {
			sleepTime = newSleepValue(sleepTime, true);
		} else {
			sleepTime = newSleepValue(sleepTime, false);
		}
		if (avProc * predictionValue < avProcNew) {
			sleepTime = newSleepValue(sleepTime, true);
		} else {
			sleepTime = newSleepValue(sleepTime, false);
		}
		if (mem * predictionValue < memNew) {
			sleepTime = newSleepValue(sleepTime, true);
		} else {
			sleepTime = newSleepValue(sleepTime, false);
		}
		return sleepTime;
	}

	public int newSleepValue(int value, boolean increase) {
		if (increase) {
			return (int) (value * predictionValue);
		} else {
			return value - ((int) (value * predictionValue) - value);
		}
	}

}
