package resources;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by koencertyn on 25/10/14.
 */
public class ConfigReader {

    private final static String[] attributes = {"platform", "process", "storage"};

    public static ArrayList<Hashtable<String,String>> getAttributes(){

        ArrayList<Hashtable<String,String>> result = new ArrayList<Hashtable<String, String>>();

        String cloudConfig = readFile("/Users/koencertyn/workspace/Middleware/src/main/resources/configs/BootConfig.conf").toLowerCase();
        int lowerCloud = 0;
        int higherCloud = cloudConfig.length();

        while(lowerCloud < higherCloud){
        	System.out.println("running");
            Hashtable<String,String> attributes = new Hashtable<String,String>();
            String selectConfig = cloudConfig.substring(lowerCloud,higherCloud);

            int lowerIndex = selectConfig.indexOf( "<cloud>" ) + ("<cloud>").length();
            System.out.println(lowerIndex);
            int higherIndex = selectConfig.indexOf( "</cloud>" );
            System.out.println(higherIndex);
            lowerCloud = lowerCloud + higherIndex + ("</cloud>").length();

            String cloudInformation = selectConfig.substring(lowerIndex,higherIndex);
            for(String attribute : ConfigReader.attributes){
                attributes.put(attribute,getAttribute(attribute,cloudInformation));
            }
            result.add(attributes);
        }
        return result;
    }

    private static String getAttribute(String attribute, String request){
        int lowerIndex = request.indexOf( "<"+attribute+">" ) + ("<"+attribute+">").length();
        System.out.println(lowerIndex);
        int higherIndex = request.indexOf( "</"+attribute+">" );
        System.out.println(higherIndex);
        return request.substring(lowerIndex,higherIndex).replaceAll("\\s+", "");
    }

    public static String readFile(String filename)
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
