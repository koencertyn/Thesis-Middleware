import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class deployTest {
	 public static final void main(String[] args) throws InterruptedException {
		 try {
	            ProcessBuilder pb = new ProcessBuilder("/bin/bash", 
	            		"/Users/koencertyn/workspace/Middleware/src/main/resources/bootscripts/herokuBoot.sh", 
	   				 "testje1", "/Users/koencertyn/test","/Users/koencertyn/workspace/Middleware");
	            final Process process = pb.start();
	            System.out.println(pb.environment());

	            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            PrintWriter pw = new PrintWriter(process.getOutputStream());
	            String line;

	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                pw.flush();
	            }
	            System.out.println("Program terminated!");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	 }

}
