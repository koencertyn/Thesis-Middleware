package resources;

public class Configs {
	
	public static final String BOOTLOCATION = "/Users/koencertyn/workspace/Middleware/src/main/resources/bootscripts/";
	
	public static final String DISBANDLOCATION = "/Users/koencertyn/workspace/Middleware/src/main/resources/disbandscripts/";
		
	public static final double PREDICTIONVALUE = 1.1;
	
	public static final String HEROKU_URL = "http://change.herokuapp.com/rest/rest/tbir";
	public static final String OPENSHIFT_URL = "http://change-thesiskcertyn.rhcloud.com/rest/tbir";
	
	public static final String BOOTTO = "/Users/koencertyn/thesis/startup";
	public static final String BOOTFROM = "/Users/koencertyn/thesis/boot";
	
	public static String getCloudUrl(String name, String cloudName){
		if(cloudName.equals("heroku")){
			return HEROKU_URL.replaceAll("change", name);
		}
		if(cloudName.equals("openshift")){
			return OPENSHIFT_URL.replaceAll("change", name);
		}
		return null;
		
	}
}
