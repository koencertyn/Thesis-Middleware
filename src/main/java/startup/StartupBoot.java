package startup;
 
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import entity.cloudInstance.CloudInstance;
import entity.cloudInstance.CloudInstanceRegistration;
import enums.Purpose;
import monitor.MonitorController;
  
@Singleton
@Startup
public class StartupBoot{
	
	@Inject
	private CloudInstanceRegistration cloud;

	@Inject
	private MonitorController controller;
  
    @PostConstruct
    private void startup() {
    	System.out.println("System started");
    	CloudInstance i = new CloudInstance();
    	i.setGoal(Purpose.CALCULATION);
    	i.setPlatform("openshift");
    	i.setPlatformInstanceName("demo");
    	i.setUrl("http://demo-thesiskcertyn.rhcloud.com/rest/tbir");
    	try {
			cloud.register(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	controller.startMonitoring();
    	
    }
  
    @PreDestroy 
    void atShutdown() {}  
}