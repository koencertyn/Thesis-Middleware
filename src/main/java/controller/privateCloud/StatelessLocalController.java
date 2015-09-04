package controller.privateCloud;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import calculation.CalculationThread;
import calculationManagement.CalculationInstance;
import calculationManagement.CalculationInstanceRegistration;
import calculationManagement.CalculationInstanceRepository;
import entity.request.Request;

@ApplicationScoped
public class StatelessLocalController {
	
	@Inject
	private CalculationInstanceRegistration reg;
	
	@Inject
	private CalculationInstanceRepository calculationRepository;
	
	public void executeRequest(Request req){
		CalculationThread t = new CalculationThread(reg, "Calculate "+req.getID(), Long.valueOf(req.getID()), req.getContent().get("1"), 
    			req.getContent().get("2"), req.getContent().get("3"));
    	System.out.println("-- : "+t);
	}

	public String retrieveResult(Request req){
		CalculationInstance member = calculationRepository.findByGivenID(Long.valueOf(req.getContent().get("1")));
        if (member == null) {
            return "no instance has been found with that ID, if the ID is correct, it might be that the cloud is still booting and processing your request!";
        }
        return member.getValue();
	}
}
