package resources;

public class Configs {
	
	public static final String BOOTLOCATION = "X";
	
	public static final String DISBANDLOCATION = "X";
		
	public static final double PREDICTIONVALUE = 1.1;
	
	public static final String HEROKU_URL = "change.herokuapp.com";
	public static final String OPENSHIFT_URL = "change-{accountName}.rhcloud.com";
	
	public static final String BOOTTO = "X";
	public static final String BOOTFROM = "X";
	
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
