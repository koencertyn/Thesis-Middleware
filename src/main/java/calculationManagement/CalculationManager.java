package calculationManagement;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@Model
public class CalculationManager {
	
	@Inject
    private FacesContext facesContext;
	
	@Inject
	private static CalculationInstanceRepository repo;
	
	@Inject
    private CalculationInstanceRegistration calculationInstanceRegistration;
	
	public void generateRandomInstance(){
		CalculationInstance inst = new CalculationInstance();
		inst.setGivenID((long) 1);
		inst.setValue("qsdfqsdfqsdf");
		try {
			calculationInstanceRegistration.register(inst);
			String errorMessage = "Random calculation has been generated";
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "CloudInstance generation successful");
            facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = "Random calculation could not be generated";
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "CloudInstance generation unsuccessful");
            facesContext.addMessage(null, m);
		}
	}

}
