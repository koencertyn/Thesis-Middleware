package controller.privateCloud;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import controller.cloud.CloudManager;
import controller.cloud.CloudPick;
import drool.instances.DroolInstance;
import entity.cloudInformation.CloudInformation;
import entity.cloudInformation.CloudInformationRepository;
import entity.request.Request;
import enums.Action;

@ApplicationScoped
public class StatelessRequestController {
	
	@Inject
	private CloudInformationRepository informationRepo;
	
	@Inject
	private StatelessLocalController local;
	
	
	@Inject
	private CloudManager cloudManager;
	
	public void evaluateRequest(Request req){
		DroolInstance newDroolInstance = new DroolInstance();
		newDroolInstance.evaluateRequest(req);
		executeRequest(req);
	}
	
	private void executeRequest(Request req){
		if(req.getAction() == null){
			local.executeRequest(req);
		}
		if(req.getAction().equals(Action.CALCULATE_LOCAL)){
			local.executeRequest(req);
		} else if(req.getAction().equals(Action.ADD_LOCALQUEUE)){
			local.executeRequest(req);
		} else if(req.getAction().equals(Action.CALCULATE_CLOUD)){
			cloudManager.forwardToAnyCloud(req);;
		} else if(req.getAction().equals(Action.CREATE_NEW_CLOUD)){
			DroolInstance newDroolInstance = new DroolInstance();
			CloudPick p = new CloudPick(req.getProperties());
			newDroolInstance.selectCloud(p);
			cloudManager.bootCloud(p.getBestCloud(), req.getPurpose());
			cloudManager.addRequestToBootingCloud(req);
		} else if(req.getAction().equals(Action.CREATE_NEW_CLOUD_PROPERTIES)){
			DroolInstance newDroolInstance = new DroolInstance();
			CloudPick p = new CloudPick(req.getProperties());
			newDroolInstance.selectCloud(p);
			cloudManager.bootCloud(p.getBestCloud(), req.getPurpose());
			cloudManager.addRequestToBootingCloud(req);
		}
		
	}
	
	public String retrieveResult(Request req){
		CloudInformation info = informationRepo.findByKey(req.getContent().get("id"));
		if(info != null){
			return cloudManager.forwardToSelectiveWithFeedback(req, info.getInstance());
		} else {
			return local.retrieveResult(req);
		}
	}

}
