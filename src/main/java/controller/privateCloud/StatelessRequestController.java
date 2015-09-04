package controller.privateCloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import resources.Configs;
import controller.cloud.CloudManager;
import controller.cloud.CloudPick;
import drool.instances.DroolInstance;
import entity.cloudInformation.CloudInformation;
import entity.cloudInformation.CloudInformationRepository;
import entity.request.Request;
import entity.request.RequestRepository;
import enums.Action;
import enums.Priority;
import enums.Property;

@ApplicationScoped
public class StatelessRequestController {
	
	@Inject
	private CloudInformationRepository informationRepo;
	
	@Inject
	private StatelessLocalController local;
	
	@Inject RequestRepository requestRepo;
	
	@Inject
	private CloudManager cloudManager;
	
	public void evaluateRequest(Request req){
		Map<String,Integer> meta = new HashMap<String, Integer>();
		meta.put("requests", requestRepo.findFromLastInterval(Configs.REQUESTINTERVAL));
		req.setMeta(meta);
		System.out.println("# requests in last interval : "+req.getMeta().get("requests"));
		DroolInstance newDroolInstance = new DroolInstance();
		newDroolInstance.evaluateRequest(req);
		System.out.println("action that should be taken :"+req.getAction());
		executeRequest(req);
	}
	
	private void executeRequest(Request req){
		if(req.getAction() == null){
			local.executeRequest(req);
		}
		if(req.getAction().equals(Action.CALCULATE_LOCAL)){
			System.out.println("calculate in private cloud");
			local.executeRequest(req);
		} else if(req.getAction().equals(Action.ADD_LOCALQUEUE)){
			System.out.println("add to queue in private cloud");
			local.executeRequest(req);
		} else if(req.getAction().equals(Action.CALCULATE_CLOUD)){
			System.out.println("calculate in any public cloud");
			cloudManager.forwardToAnyCloud(req);;
		} else if(req.getAction().equals(Action.CREATE_NEW_CLOUD)){
			System.out.println("boot new public cloud");
			DroolInstance newDroolInstance = new DroolInstance();
			CloudPick p = new CloudPick(req.getProperties());
			newDroolInstance.selectCloud(p);
			cloudManager.bootCloud("openshift", req.getPurpose());
			cloudManager.addRequestToBootingCloud(req);
		} else if(req.getAction().equals(Action.CREATE_NEW_CLOUD_PROPERTIES)){
			System.out.println("boot new cloud with properties");
			DroolInstance newDroolInstance = new DroolInstance();
			CloudPick p = new CloudPick(req.getProperties());
			newDroolInstance.selectCloud(p);
			cloudManager.bootCloud(p.getBestCloud(), req.getPurpose());
			cloudManager.addRequestToBootingCloud(req);
		} 
		
	}
	
	public String retrieveResult(Request req){
		if(informationRepo.findByKey(req.getContent().get("1")) != null){
			System.out.println("trying it to fetch in public");
			return cloudManager.forwardToSelectiveWithFeedback(req, informationRepo.findByKey(req.getContent().get("1")).getInstance());
		}
		System.out.println("Fetching respons in private cloud");
		return local.retrieveResult(req);
	}

	public  ArrayList<Property> getPropertiesFromString(String properties){
		ArrayList<Property> list = new ArrayList<Property>();
		String[] res = properties.split("#");
		for(String pr : res){
			if(getPropertyFromString(pr) != null){
				list.add(getPropertyFromString(pr));
			}
		}
		return list;
	}
	
	public Priority getPriority(String priorities){
		String[] res = priorities.split("#");
		for(String pr : res){
			if(pr.contains("priority")){
				return getPriorityFromString(pr);
			}
		}
		return null;
	}
	
	private Priority getPriorityFromString(String propertyString){
		String[] res = propertyString.split("=");
		if(res[1].equals("low")){
			return Priority.LOW;
		} else if (res[1].equals("medium")){
			return Priority.MEDIUM;
		} else if (res[1].equals("high")){
			return Priority.HIGH;
		} else {
			return null;
		}
	}
	
	private  Property getPropertyFromString(String property){
		if(property.contains("nocloud")){
			return Property.NOCLOUD;
		} else if(property.contains("cloud")){
			return Property.CLOUD;
		} else if(property.contains("cheap")){
			return Property.CHEAP;
		} else if(property.contains("encrypted")){
			return Property.ENCRYPTED;
		} else if(property.contains("fast")){
			return Property.FAST;
		} else if(property.contains("bigdata")){
			return Property.BIG_DATA;
		} else if(property.contains("new")){
			return Property.NEWCLOUD;
		} else {
			return null;
		}
		
	}

}
