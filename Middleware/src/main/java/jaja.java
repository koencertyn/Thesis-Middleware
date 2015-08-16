import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import calculationManagement.CalculationInstance;
import calculationManagement.CalculationInstanceRegistration;
import entity.request.Request;


public class jaja {

	
	public void calculate(Request req){
		CalculationInstance in = new CalculationInstance();
		in.setGivenID(req.getID());
		CalculationInstanceRegistration rep = new CalculationInstanceRegistration();
		System.out.println("created calculation instance with id: "+req.getID());
		in.setValue("qsdfdqfsqsdf");
		try {
			rep.register(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("de localcontroller is gecalled");
		
	}
	public void test(){
		
	}
	
	//TODO
	public static void addToCalculationQueue(Request req){
		
	}
	
	public static void store(Request req){
 
        try {            
 
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(req.getContent().toString());
            // Write a image byte array into file system
            FileOutputStream imageOutFile = new FileOutputStream(
                    "/Users/koencertyn/rose2.jpg");
 
            imageOutFile.write(imageByteArray);
 
            imageOutFile.close();
 
            System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
 
    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
        return null;//Base64.encodeBase64URLSafeString(imageByteArray);
    }
 
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return null;//Base64.decodeBase64(imageDataString);
    }
}
