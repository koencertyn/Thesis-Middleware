package calculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CalculationClass {
	public static boolean ASC = true;
    public static boolean DESC = false;

    public static String fileName= "/Users/koencertyn/workspace/Middleware/text.txt";

    public static String guessResult(String relation1, String relation2, String goal){
        try {
            HashMap<String, Double> test = getClosestWords(calculateVectorSum(calculateVectorDiff(getVector(goal),getVector(relation1)),getVector(relation2)));

            List<String> keys = new ArrayList<String>(test.keySet());
            String result = "";
            int numbers = 0;
            for (String key: keys) {
                if(! (key.equals(relation1) || key.equals(relation2) || key.equals(goal))){
                    result += "\n" + key + "  "+test.get(key);
                }
                if(numbers == 10)
                	return result;
                else
                	numbers++;
            }
            return result;
        } catch (Exception e){
        	return "faulty entries added";
        }
    }

    public static double[] getVector(String word){
        double[] vectors = null;
        //READING FILE VECTORS
        try{

            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
            boolean found = false;
            while (! found && (line = bufferReader.readLine()) != null)   {
                if(calculateWord(line).equals(word)){
                    vectors = calculateVector(line);
                }
            }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }
        System.out.println("found vector");
        return vectors;
    }

    public static HashMap<String,Double> getClosestWords(double[] startVec){
        HashMap<String,Double> res = new HashMap<String, Double>();
        //READING FILE VECTORS
        try{

            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            while ((line = bufferReader.readLine()) != null)   {
                double a = cosineSimilarity(calculateVector(line),startVec);
                if(Math.abs(a) > 0.5){
                    res.put(calculateWord(line),a);
                }
            }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }

        HashMap<String, Double> sortedMapDesc = sortByComparator(res, DESC);

        return sortedMapDesc;
    }

    public static String calculateWord(String line){
        String firstWord = null;
        if(line.contains(" ")){
            firstWord= line.substring(0, line.indexOf(" "));
        }
        return firstWord;
    }

    public static double[] calculateVectorDiff(double[] v1, double[] v2){
        if(v1.length != v2.length)
            throw new IllegalArgumentException("Cannot calc 2 vectors of other size");
        double[] res = new double[v1.length];
        for (int i = 0 ; i < v1.length ; i++){
            res[i] = v1[i] - v2[i];
        }
        return res;
    }

    public static double[] calculateVectorSum(double[] v1, double[] v2){
        if(v1.length != v2.length)
            throw new IllegalArgumentException("Cannot calc 2 vectors of other size");
        double[] res = new double[v1.length];
        for (int i = 0 ; i < v1.length ; i++){
            res[i] = v1[i] + v2[i];
        }
        return res;
    }

    public static double[] calculateVector(String line){
        String[] vector = line.split("\\s+");
        return convertStringTodouble(vector);
    }

    private static double[] convertStringTodouble(String[] vectorList){
        boolean first = true;
        double[] res = new double[300];
        int i = 0;
        for (String str : vectorList){
            //Remove the first entry of the list (this is the word itself)
            if(! first){
                res[i] = Double.parseDouble(str);
                i++;
            } else {
                first = false;
            }
        }

        return res;
    }

    /**
     * Method to calculate cosine similarity between two documents.
     * @param docVector1 : document vector 1 (a)
     * @param docVector2 : document vector 2 (b)
     * @return
     */
    public static double cosineSimilarity(double[] docVector1, double[] docVector2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < docVector1.length; i++) //docVector1 and docVector2 must be of same length
        {
            dotProduct += docVector1[i] * docVector2[i];  //a.b
            magnitude1 += Math.pow(docVector1[i], 2);  //(a^2)
            magnitude2 += Math.pow(docVector2[i], 2); //(b^2)
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }

    public static HashMap<String, Double> sortByComparator(HashMap<String, Double> unsortMap, final boolean order)
    {

        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Double>>()
        {
            @Override
			public int compare(Entry<String, Double> o1,
                               Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        HashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
