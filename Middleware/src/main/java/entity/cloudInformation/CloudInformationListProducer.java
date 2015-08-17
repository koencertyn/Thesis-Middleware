package entity.cloudInformation;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class CloudInformationListProducer {

    @Inject
    private CloudInformationRepository informationRepository;

    private List<CloudInformation> instances;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<CloudInformation> getCloudInformations() {
        return instances;
    }
}
