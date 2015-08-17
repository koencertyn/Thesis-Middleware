package controller.privateCloud;

import java.util.Date;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import entity.request.Request;
import entity.request.RequestRegistration;
import enums.Priority;

@Model
public class RequestController {
	
	
	@Inject
    private FacesContext facesContext;
	
	@Inject
    private RequestRegistration requestRegistration;
	
	public void generateRequest(Request request) throws Exception {
		System.out.println("generated request");
		requestRegistration.register(request);
	}
	
	public void generateRandomRequest(){
		Request req = new Request();
		req.setPriority(Priority.HIGH);
		req.setRequestDate(new Date());
		try {
			requestRegistration.register(req);
			String errorMessage = "Random request has been generated";
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Request generation successful");
            facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = "Random request could not be generated";
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Request generation unsuccessful");
            facesContext.addMessage(null, m);
		}
	}
}
