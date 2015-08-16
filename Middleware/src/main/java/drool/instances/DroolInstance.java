package drool.instances;

import java.util.HashMap;

import monitor.MonitorMeasurement;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import entity.request.Request;

public class DroolInstance {
	
	KieServices ks;
	KieContainer kContainer;
	static KieSession kSession;
	
	public DroolInstance(){
//		KieServices kieServices = KieServices.Factory.get();
//		ReleaseId releaseId = kieServices.newReleaseId( "org.acme", "myartifact", "1.0-SNAPSHOT" );
//		KieContainer kContainer = kieServices.newKieContainer( releaseId );
//		KieScanner kScanner = kieServices.newKieScanner( kContainer );
// 		Start the KieScanner polling the Maven repository every 10 seconds
//		kScanner.start( 10000L );
//		ks = KieServices.Factory.get();
//		kContainer = ks.getKieClasspathContainer();
//		kSession = kContainer.newKieSession("ksession-rules");
		 ks = KieServices.Factory.get();
	    kContainer = ks.getKieClasspathContainer();
    	kSession = kContainer.newKieSession("ksession-rules");
	}
	
	public void evaluateRequest(Request req){
		kSession.insert(req);
		kSession.fireAllRules();
	}
	
	public void selectCloud(HashMap<String, String> properties){
		kSession.insert(properties);
		kSession.fireAllRules();
	}
	
	public void evaluateMonitoring(MonitorMeasurement measurement){
		kSession.insert(measurement);
		kSession.fireAllRules();
	}
}
