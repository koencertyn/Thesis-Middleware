package entity.cloudInstance;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class CloudInstanceListProducer {

    @Inject
    private CloudInstanceRepository cloudInstanceRepository;

    private List<CloudInstance> instances;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<CloudInstance> getCloudInstances() {
        return instances;
    }
}
