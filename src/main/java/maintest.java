

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * This is a sample class to launch a rule.
 */
public class maintest {
	
	private final static String bootLocation = "/Users/koencertyn/workspace/Middleware/src/main/resources/bootscripts/";
    public static final void main(String[] args) throws InterruptedException {
      try {
      /* String array to execute commands */
          String[] command = new String[3];
          command[0] = "heroku";
          command[1] = "create";
          command[2] = "updatetestje";
      /* Command you want to execute */


      /* Create process */
          Process p = Runtime.getRuntime().exec(command);

      /* Get OuputStream */
          PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(p.getOutputStream())), true);

      /* Read the output of command prompt */
          BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
          String line = reader.readLine();
      /* Read upto end of execution */
          while (line != null) {
          /* Pass the value to command prompt/user input */
              writer.println("08-08-2014");
              System.out.println(line);
              line = reader.readLine();
          }
      /* The stream obtains data from the standard output stream of the process represented by this Process object. */
          BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
      /* The stream obtains data from the error output stream of the process represented by this Process object. */
          BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

          String Input;
          while ((Input = stdInput.readLine()) != null) {
              System.out.println(Input);
          }

          String Error;
          while ((Error = stdError.readLine()) != null) {
              System.out.println(Error);
          }

      } catch (Exception e) {
          e.printStackTrace();
      }

      try {
      /* String array to execute commands */
          String[] command = new String[1];
          command[0] = "ls";
      /* Command you want to execute */

      /* Create process */
          Process p = Runtime.getRuntime().exec(command);

      /* Get OuputStream */
          PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(p.getOutputStream())), true);

      /* Read the output of command prompt */
          BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
          String line = reader.readLine();
      /* Read upto end of execution */
          while (line != null) {
          /* Pass the value to command prompt/user input */
              writer.println("08-08-2014");
              System.out.println(line);
              line = reader.readLine();
          }
      /* The stream obtains data from the standard output stream of the process represented by this Process object. */
          BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
      /* The stream obtains data from the error output stream of the process represented by this Process object. */
          BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

          String Input;
          while ((Input = stdInput.readLine()) != null) {
              System.out.println(Input);
          }

          String Error;
          while ((Error = stdError.readLine()) != null) {
              System.out.println(Error);
          }

      } catch (Exception e) {
          e.printStackTrace();
      }

}

public static void OSCommand(String a)
{
  Runtime rt = Runtime.getRuntime();
  try
  {
      rt.exec("cd");
      Process p = rt.exec(a);
      Reader in = new InputStreamReader(p.getInputStream());
      int c = in.read();
      while (c!=-1)
      {
          System.out.print((char)c);
          c = in.read();
      }
  }
  catch (IOException e)
  {
      System.out.println(e.toString());
  }
}

}
