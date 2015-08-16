package rest.services;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.privateCloud.RequestController;
import entity.request.RequestRepository;

@Path("/privateCloud")
@RequestScoped
public class LocalInfoResourceRestService {
	@Inject
    private RequestRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> listAllRequests() throws Exception {
    	ArrayList<String> result = new ArrayList<String>();
    	result.add("TotalRequests : "+repository.findFromLastInterval(60));
    	return result;
    }

}
