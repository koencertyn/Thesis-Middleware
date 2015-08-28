package drool.instances;

import monitor.MonitorMeasurement;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import controller.cloud.CloudPick;
import entity.request.Request;

public class DroolInstance {
	
	KieServices ks;
	KieContainer kContainer;
	static KieSession kSession;
	
	public DroolInstance(){
		 ks = KieServices.Factory.get();
	    kContainer = ks.getKieClasspathContainer();
    	kSession = kContainer.newKieSession("ksession-rules");
	}
	
	public void evaluateRequest(Request req){
		kSession.insert(req);
		kSession.fireAllRules();
	}
	
	public void selectCloud(CloudPick p){
		kSession.insert(p);
		kSession.fireAllRules();
	}
	
	public void evaluateMonitoring(MonitorMeasurement measurement){
		kSession.insert(measurement);
		kSession.fireAllRules();
	}
}
