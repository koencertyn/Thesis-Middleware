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

@Path("/tbir")
@Produces(MediaType.APPLICATION_JSON)
public class Calculation {
	
	@Inject
	private CalculationInstanceRepository calculationRepository;
	
	@Inject
	private CalculationInstanceRegistration reg;
	
    @GET
    @Path("{relation1}/{relation2}/{goalRelation1}/{id}")
    public String getBasic(@PathParam("relation1") String relation1,
    		@PathParam("relation2") String relation2,
    		@PathParam("goalRelation1") String goalRelation,
    		@PathParam("id") Long id) {
    	CalculationThread t = new CalculationThread(reg, "Calculate "+id, id, relation1, relation2, goalRelation);
        return id.toString();
    }
	
	@GET
    @Path("/id/{id}")
    public String getWithPropertiqsdfqsdf(@PathParam("id") String id) {
    	System.out.println("called");
    	CalculationInstance i = new CalculationInstance();
    	i.setGivenID(Long.valueOf(id));
    	i.setValue("qsdfqsdf");
    	try {
    		reg.register(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return id;
    }
    
    @GET
    @Path("/{givenID}")
    public String get(@PathParam("givenID") Long id) {;
		CalculationInstance member = calculationRepository.findByGivenID(id);
        if (member == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return member.getValue();
    }
    
    @GET
    @Path("/status")
    public String getStatus() {;
		return "idle";
    }

}