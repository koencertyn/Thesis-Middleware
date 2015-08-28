package calculation;

import java.io.PrintStream;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ejb.TransactionAttribute;

import calculationManagement.CalculationInstance;
import calculationManagement.CalculationInstanceRegistration;

public class CalculationThread implements Runnable {
	
	   private Thread t;
	   private String threadName;
	   
	   private CalculationInstanceRegistration reg;
	   
	   //We do this because of the deprecation of the stopthread method.
	   private boolean mayRun = true;
	  
	   private Long id;
	   private String one;
	   private String two;
	   private String goal;
	   
	   public CalculationThread(CalculationInstanceRegistration reg, String name, Long id,
			   String one, String two, String goal) {
		   this.reg = reg;
		   this.one = one;
		   this.two = two;
		   this.goal = goal;
		   this.id = id;
		   this.threadName = name;
	       System.out.println("Creating " +  threadName + "   with variables : "+one+ "  "+two+"  "+goal);
	       start();
	}
	@Override
	   public void run() {
		   System.out.println("--- RUNNING PRIVATE CLOUD CALCULATION ----");
		   CalculationInstance i = new CalculationInstance();
	    	i.setGivenID(this.id);
	    	i.setValue(CalculationClass.guessResult(one,two,goal));
	    	try {
	    		reg.register(i);
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