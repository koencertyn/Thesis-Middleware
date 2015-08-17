package controller.cloud;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import enums.Property;

/**
 * Created by koencertyn on 25/10/14.
 */
public class CloudChecker {

    private static final String[] attributes = {"platform", "encryption", "log", "datamodel","processing","deployment","cost"};

    public static ArrayList<Hashtable<String,String>> getAttributes(){

        ArrayList<Hashtable<String,String>> result = new ArrayList<Hashtable<String, String>>();

        String cloudConfig = readFile("/Users/koencertyn/workspace/Middleware/src/main/resources/configs/cloudConfig.conf").toLowerCase();
        int lowerCloud = 0;
        int higherCloud = cloudConfig.length();

        while(lowerCloud < higherCloud){
            Hashtable<String,String> attributes = new Hashtable<String,String>();
            String selectConfig = cloudConfig.substring(lowerCloud,higherCloud);

            int lowerIndex = selectConfig.indexOf( "<cloud>" ) + ("<cloud>").length();
            int higherIndex = selectConfig.indexOf( "</cloud>" );

            lowerCloud = lowerCloud + higherIndex + ("</cloud>").length();

            String cloudInformation = selectConfig.substring(lowerIndex,higherIndex);
            for(String attribute : CloudChecker.attributes){
                attributes.put(attribute,getAttribute(attribute,cloudInformation));
            }
            result.add(attributes);
        }
        return result;
    }
    
    public static ArrayList<String> getCloudNames(){
    	ArrayList<String> result = new ArrayList<String>();
    	for(Hashtable<String, String> a : CloudChecker.getAttributes()){
			result.add(a.get("platform"));
		}
    	return result;
    }
     
    public static Hashtable<String,String> getCloudAttributes(String cloud){
    	ArrayList<Hashtable<String, String>> allAttributes = getAttributes();
    	for ( Hashtable<String, String> att : allAttributes){
    		if(att.containsValue(cloud))
    			return att;
    	}
    	return null;
    }
    
    public static int cloudScore(String cloudName, ArrayList<Property> properties){
    	int score = 0;
    	Hashtable<String, String> cloudConfig = getCloudAttributes(cloudName);
    	for (Property prop : properties) {
    		switch (prop) {
	    		case FAST:
	    			if(cloudConfig.get("deployment").equals("average")){
	    				score += 1;
	    			} else if (cloudConfig.get("deployment").equals("extended")){
	    				score += 3;
	    			} else {
	    				score -= 1;
	    			}
	    			if(cloudConfig.get("processing").equals("average")){
	    				score += 1;
	    			} else if (cloudConfig.get("processing").equals("extended")){
	    				score += 3;
	    			} else {
	    				score += 0;
	    			}
	    			break;
	    		case CHEAP:
	    			if(cloudConfig.get("cost").equals("average")){
	    				score += 1;
	    			} else if (cloudConfig.get("cost").equals("low")){
	    				score += 3;
	    			} else {
	    				score -= 1;
	    			}
	    			break;
	    		case ENCRYPTED:
	    			if(cloudConfig.get("encryption").equals("yes")){
	    				score += 3;
	    			} else {
	    				score -= 3;
	    			}
	    			break;	    			
	
	    		default:
	    			break;
	    	}
		}
    	
    	return score;
    }

    private static String getAttribute(String attribute, String request){
        int lowerIndex = request.indexOf( "<"+attribute+">" ) + ("<"+attribute+">").length();
        int higherIndex = request.indexOf( "</"+attribute+">" );
        return request.substring(lowerIndex,higherIndex).replaceAll("\\s+", "");
    }

    private static String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
