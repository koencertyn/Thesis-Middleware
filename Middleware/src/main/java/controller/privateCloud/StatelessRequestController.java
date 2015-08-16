package controller.privateCloud;

import javax.inject.Inject;

import controller.cloud.CloudManager;
import drool.instances.DroolInstance;
import entity.cloudInformation.CloudInformation;
import entity.cloudInformation.CloudInformationRepository;
import entity.request.Request;
import entity.request.RequestRegistration;
import entity.request.RequestRepository;
import enums.Action;

public class StatelessRequestController {
	
	@Inject
	private RequestRegistration requestRegistration;
	
	@Inject
	private CloudInformationRepository informationRepo;
	
	@Inject
	private StatelessLocalController local;
	
	@Inject
	private CloudManager cloudManager;
	
	public void evaluateRequest(Request req){
		try {
			requestRegistration.register(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DroolInstance newDroolInstance = new DroolInstance();
		newDroolInstance.evaluateRequest(req);
		executeRequest(req);
	}
	
	private void executeRequest(Request req){
		if(req.getAction().equals(Action.CALCULATE_LOCAL)){
			local.executeRequest(req);
		} else if(req.getAction().equals(Action.ADD_LOCALQUEUE)){
			local.executeRequest(req);
		} else if(req.getAction().equals(Action.CALCULATE_CLOUD)){
			cloudManager.forwardToAnyCloud(req);;
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
