package rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entity.cloudInstance.CloudInstance;
import entity.cloudInstance.CloudInstanceRepository;

@Path("/instance")
@RequestScoped
public class CloudInstanceResourceRestService {

    @Inject
    private CloudInstanceRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CloudInstance> listAllRequests() throws Exception {
    	return repository.findAll();
    }
}	
