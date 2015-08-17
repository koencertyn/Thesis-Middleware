package startup;
 
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import monitor.MonitorController;
  
@Singleton
@Startup
public class StartupBoot{

	@Inject
	private MonitorController controller;
  
    @PostConstruct
    private void startup() {
    	System.out.println("System started");
    	controller.startMonitoring();
    	
    }
  
    @PreDestroy 
    void atShutdown() {}  
}