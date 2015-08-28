package rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entity.request.Request;
import entity.request.RequestRepository;

@Path("/requests")
@RequestScoped
public class RequestResourceRestService {

    @Inject
    private RequestRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Request> listAllRequests() throws Exception {
    	return repository.findAll();
    }
}	
