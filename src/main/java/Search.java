import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.privateCloud.RequestController;
import drool.instances.DroolInstance;
import entity.request.Request;
import enums.Purpose;


 
@WebServlet(name = "search",urlPatterns = {"/search/*"})
@MultipartConfig
public class Search extends HttpServlet {
	
	@Inject
	private RequestController rc;
	
	@Inject
	private DroolInstance drool;
 
 
  private static final long serialVersionUID = 2857847752169838915L;

 
  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println(request.getParameterMap());
	  Request req = new Request();
	  req.setPurpose(Purpose.CALCULATION);
	  drool.evaluateRequest(req);
	  
	  try {
		rc.generateRequest(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  //forwardRequest("POST", request, response);
	  
  }
}