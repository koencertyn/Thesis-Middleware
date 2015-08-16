package controller.cloud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import monitor.cloud.CloudMonitor;
import entity.cloudInstance.CloudInstance;
import entity.cloudInstance.CloudInstanceRegistration;
import entity.cloudInstance.CloudInstanceRepository;
import entity.request.Request;
import enums.Purpose;
@Model
public class CloudManager {
	
	private final static String defaultCloud = "openshift";
	
	@Inject
    private FacesContext facesContext;
	
	@Inject
	private static CloudInstanceRepository repo;
	
	@Inject
    private CloudInstanceRegistration cloudInstanceRegistration;
	
	@Inject
	private CloudMonitor cloudMonitor;
	
	
	private CloudBooter latestBooter;
	
	public void forwardToAnyCloud(Request req){
		connectToUrl(buildUrl(repo.findCertainInstance(req.getPurpose()), req));
	}
	
	public String forwardToAnyCloudWithFeedback(Request req){
		return connectToUrlWithFeedback(buildUrl(repo.findCertainInstance(req.getPurpose()), req));
	}
	
	public void forwardToSelective(Request req, CloudInstance ins){
		connectToUrl(buildUrl(ins, req));
	}
	
	public String forwardToSelectiveWithFeedback(Request req, CloudInstance ins){
		return connectToUrlWithFeedback(buildUrl(ins, req));
	}
	
	public void bootCloud(String cloudName, Purpose purpose){
		CloudBooter booter = new CloudBooter();
		booter.bootCloud(cloudName, purpose);
		this.latestBooter = booter;
	}
	
	public void addRequestToBootingCloud(Request req){
		if(this.latestBooter != null)
			this.latestBooter.addRequest(req);
	}
	
	public void disbandCloud(CloudInstance instance){
		CloudDisbander disbander = new CloudDisbander();
		disbander.disbandCloud(instance.getPlatform(), instance);
	}
	
	public void startMonitoring(CloudInstance instance){
		cloudMonitor.startMonitoring(instance);
	}
	
	private String buildUrl(CloudInstance inst, Request req){
		String str ="";
		str += inst.getUrl();
		for(String partialReq : req.getContent().values()){
			str+="/"+partialReq;
		}
		return str;
	}
	
	private void connectToUrl(String destinationUrl){
		URL url;
		try {
			url = new URL(destinationUrl);
		
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String connectToUrlWithFeedback(String destinationUrl){
		URL url;
		String result = "";
		try {
			url = new URL(destinationUrl);
		
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            result+=" "+inputLine;
        in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean hasAvailableStorageCloud(){
		return (repo.findCertainInstance(Purpose.STORAGE) != null);
	}
	
	public static boolean hasAvailableCalculationCloud(){
		return (repo.findCertainInstance(Purpose.CALCULATION) != null);
	}

	public void bootBackupCloud(){
		if(repo.findNonUsedInstance().isEmpty()){
			CloudBooter newBooter = new CloudBooter();
			newBooter.bootCloud(defaultCloud,Purpose.NONE);
		} else {
			System.out.println("already have newly booted cloud up.");
		}
	}
	
	public void generateInstance(CloudInstance instance) throws Exception {
		cloudInstanceRegistration.register(instance);
	}
	
	public void generateRandomInstance(){
		CloudInstance req = new CloudInstance();
		try {
			cloudInstanceRegistration.register(req);
			String errorMessage = "Random cloudInstance has been generated";
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "CloudInstance generation successful");
            facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = "Random CloudInstance could not be generated";
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "CloudInstance generation unsuccessful");
            facesContext.addMessage(null, m);
		}
	}
}

