import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import calculation.CalculationClass;
import controller.privateCloud.StatelessRequestController;


public class main {

	public static void main(String[] args) throws IOException {
		System.out.println(CalculationClass.guessResult("man", "woman", "king"));
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
