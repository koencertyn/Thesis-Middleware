package calculation;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import calculationManagement.CalculationInstance;
import calculationManagement.CalculationInstanceRegistration;
import calculationManagement.CalculationInstanceRepository;
import controller.cloud.CloudBooter;
import controller.privateCloud.StatelessLocalController;
import controller.privateCloud.StatelessRequestController;
import entity.request.Request;
import entity.request.RequestRegistration;
import entity.request.RequestRepository;
import enums.Purpose;

@Path("/tbir")
@Produces(MediaType.APPLICATION_JSON)
public class Calculation {
 
	@Inject
	private StatelessRequestController cont;
	
	@Inject
	private CalculationInstanceRepository calculationRepository;
	
	@Inject
	private CalculationInstanceRegistration reg;
	
	@Inject
	private RequestRegistration requestRegistration;
	
	@Inject
	private RequestRepository requestRepo;
	
    @GET
    @Path("{relation1}/{relation2}/{goalRelation1}")
    public String getBasic(@PathParam("relation1") String relation1,
    		@PathParam("relation2") String relation2,
    		@PathParam("goalRelation1") String goalRelation) {
    	Request req = new Request();
    	req.setPurpose(Purpose.CALCULATION);
    	HashMap<String, String> content = new HashMap<String,String>();
    	content.put("1", relation1);
    	content.put("2", relation2);
    	content.put("3", goalRelation);
    	req.setContent(content);
    	try {
			requestRegistration.register(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	cont.evaluateRequest(req);
        return req.getID().toString();
    }
	
	@GET
    @Path("{relation1}/{relation2}/{goalRelation1}/{properties}")
    public String getWithProperties(@PathParam("relation1") String relation1,
    		@PathParam("relation2") String relation2,
    		@PathParam("goalRelation1") String goalRelation,
    		@PathParam("properties") String properties) {
    	Request req = new Request();
    	req.setPurpose(Purpose.CALCULATION);
    	req.setPriority(cont.getPriority(properties));
    	req.setProperties(cont.getPropertiesFromString(properties));
    	HashMap<String, String> content = new HashMap<String,String>();
    	content.put("1", relation1);
    	content.put("2", relation2);
    	content.put("3", goalRelation);
    	req.setContent(content);
    	try {
			requestRegistration.register(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	cont.evaluateRequest(req);
        return req.getID().toString();
    }
    
    @GET
    @Path("/results/{id}")
    public String get(@PathParam("id") Long id) {;
	    Request req = new Request();
		req.setPurpose(Purpose.CALCULATION);
		HashMap<String, String> content = new HashMap<String,String>();
    	content.put("1", id.toString());
    	req.setContent(content);
		try {
			requestRegistration.register(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return cont.retrieveResult(req);
    }
    
    @GET
    @Path("/requests/{interval}")
    public int getRequests(@PathParam("interval") int id) {
    	return requestRepo.findFromLastInterval(id);
    }

}