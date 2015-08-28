package entity.request;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class RequestListProducer {

    @Inject
    private RequestRepository requestRepository;

    private List<Request> requests;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Request> getRequests() {
        return requests;
    }
}
