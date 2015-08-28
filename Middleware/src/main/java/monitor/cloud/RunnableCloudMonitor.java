package monitor.cloud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import entity.cloudInstance.CloudInstance;

class RunnableCloudMonitor implements Runnable {
	
	   private Thread t;
	   private String threadName;
	   private CloudInstance instance;
	   private CloudMonitor monitor;
	   
	   private final int parameter = 10;
	   
	   //We do this because of the deprecation of the stopthread method.
	   private boolean mayRun = true;
	   
	   RunnableCloudMonitor(CloudMonitor monitor, String name, CloudInstance instance){
	       this.monitor = monitor;
		   threadName = name;
	       this.instance = instance;
	       System.out.println("Creating " +  threadName );
	   }
	   @Override
	   public void run() {
		   System.out.println("--- RUNNING public MONITOR ----");
		   int idleCount = 1;
		   URL url;
			try {
				url = new URL(this.instance.getUrl()+"/blob/test");
				while(mayRun){
					BufferedReader in = new BufferedReader(
			        new InputStreamReader(url.openStream()));
			        String inputLine;
			        while ((inputLine = in.readLine()) != null){
			        	System.out.println("read line : "+inputLine);
			        	if(inputLine.equals("testje jaja") && idleCount > parameter){
			        		this.monitor.disableCloud(this.instance);
			        		stop();
			        	}
			        	else if(inputLine.equals("testje jaja")){
			        		System.out.println("measured idle cloud :"+this.instance.getUrl());
			        		idleCount++;
			        	}
			        	Thread.sleep(5000);
			        }
			        in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	   
	   public void start ()
	   {
	      System.out.println("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	   
	   public void stop() {
		   System.out.println("Stopping "+ threadName);
		   if(t!= null){
			   this.mayRun = false;
		   }
	   }

	}