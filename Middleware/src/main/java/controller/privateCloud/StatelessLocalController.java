package controller.privateCloud;

import entity.request.Request;

public class StatelessLocalController {
	public void executeRequest(Request req){
		//HAS TO BE IMPLEMENTED BY PROVIDER
		//This can be done by retrieving the components from the request and 
		//directing them here to the backend components.
	}
	
	public String retrieveResult(Request req){
		//HAS TO BE IMPLEMENTED BY PROVIDER
		//This can be done by retrieving the components from the request and 
		//directing them here to the backend components.
		return null;
	}
}
