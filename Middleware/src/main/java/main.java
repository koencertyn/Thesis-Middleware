import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import controller.cloud.CloudBooter;
import enums.Purpose;


public class main {

	public static void main(String[] args) throws IOException {
		

	}
	
	public static int highest(int[] list){
        
        int max = list[0];
        int i;
        for(i = 0; i <= list.length-1; i++) {
            if(list[i] > max)
            	max = list[i];
        }
        
        return max;
    }

}
