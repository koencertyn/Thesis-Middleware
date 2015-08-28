package calculationManagement;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class CalculationInstanceListProducer {

    @Inject
    private CalculationInstanceRepository cloudInstanceRepository;

    private List<CalculationInstance> instances;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<CalculationInstance> getCalculationInstances() {
        return instances;
    }
}
