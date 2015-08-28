package controller.cloud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import resources.Configs;
import entity.cloudInstance.CloudInstance;
import entity.cloudInstance.CloudInstanceRegistration;
import entity.request.Request;
import enums.Purpose;

public class CloudBooter {
	
    private CloudInstanceRegistration cloudInstanceRegistration;
      
    private CloudManager cloudManager;
	
	private List<Request> requests = new ArrayList<Request>();

	public void bootCloud(String cloudName,Purpose purpose, CloudInstanceRegistration cloudRegistration, CloudManager manager) {
		this.cloudInstanceRegistration = cloudRegistration;
		this.cloudManager = manager;
		runBootFile(cloudName,purpose);
	}

	private void runBootFile(String name, Purpose purpose) {
		BootingThread R1 = new BootingThread(name, purpose);
		R1.start();
	}

	class BootingThread implements Runnable {
		
		private Thread t;
		private final String threadName = "bootThread";
		private String cloudName;
		private Purpose purpose;

		BootingThread(String cloudName, Purpose purpose) {
			this.purpose = purpose;
			this.cloudName = cloudName;
			System.out.println("Creating " + threadName);
		}

		@Override
		public void run() {
			System.out.println("--- RUNNING CLOUD BOOTER ----");
			String name = "demokoencertyn"+ (int) (Math. random()*50 + 1);
			try {
				ProcessBuilder pb = new ProcessBuilder("/bin/bash", 
	            		Configs.BOOTLOCATION+cloudName+"Boot.sh", name, Configs.BOOTTO,Configs.BOOTFROM);
	            final Process process = pb.start();

	            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            PrintWriter pw = new PrintWriter(process.getOutputStream());
	            String line;

	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                pw.flush();
	            }
	            System.out.println("Program terminated!");
				CloudInstance req = new CloudInstance();
				req.setGoal(purpose);         
				req.setPlatform(cloudName);
				req.setPlatformInstanceName(name);
				req.setUrl(Configs.getCloudUrl(name, cloudName));
				try {
					cloudInstanceRegistration.register(req);
					cloudManager.startMonitoring(req);
					System.out.println("started monitoring");
				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("Script booting executed successfully for cloud :"+ cloudName);
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Thread " + threadName + " interrupted.");
			}
			System.out.println("Thread " + threadName + " exiting.");
			executeRequests();
		}

		private void executeRequests() {
			for(Request r : requests){
				
			}
			
		}

		public void start() {
			System.out.println("Starting " + threadName);
			if (t == null) {
				t = new Thread(this, threadName);
				t.start();
			}
		}

		public void stop() {
			System.out.println("Thread will only stop after cloud deployment.");
		}

	}

	public void addRequest(Request req) {
		requests.add(req);
	}

}
