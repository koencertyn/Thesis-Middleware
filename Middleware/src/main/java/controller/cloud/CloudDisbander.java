package controller.cloud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import entity.cloudInstance.CloudInstance;

public class CloudDisbander {
	// TODO config file
	private final String disbandLocation = "/Users/koencertyn/workspace/Middleware/src/main/resources/disbandscripts/";
	
	public void disbandCloud(String cloudName,CloudInstance instance) {
		runDisbandFile(cloudName,instance);
	}

	private void runDisbandFile(String name,CloudInstance instance) {
		DisbandingThread R1 = new DisbandingThread(name, instance);
		R1.start();
	}
	
	class DisbandingThread implements Runnable {
		private Thread t;
		private final String threadName = "disbandThread";
		private String cloudName;
		private CloudInstance instance;
		
		@Inject
		private EntityManager manager;

		DisbandingThread(String cloudName, CloudInstance instance) {
			this.instance = instance;
			this.cloudName = cloudName;
			System.out.println("Creating " + threadName);
		}

		@Override
		public void run() {
			System.out.println("Running " + threadName);
			try {
				ProcessBuilder pb = new ProcessBuilder("/bin/bash", 
						disbandLocation+cloudName+"Disband.sh", instance.getUrl());
	            final Process process = pb.start();
	            System.out.println(pb.environment());

	            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            PrintWriter pw = new PrintWriter(process.getOutputStream());
	            String line;

	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                pw.flush();
	            }
	            System.out.println("Program terminated!");
				try {
					manager.remove(instance);
				} catch (Exception e) {
				}
				System.out.println("Script booting executed successfully for cloud :"
								+ cloudName);
			} catch (Exception e) {
				System.out.println(e);
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
			System.out.println("Thread will only stop after cloud disbandment.");
		}

	}

}