/*
Implementing a Stack 
Haoran Cheng
hc633
 */
package stack;
import java.io.*;
import java.util.*;

public class Stack
{
 String fname= "numbers.txt";
 public List<String> numbers = new ArrayList<String>(); 
 
    private void run()
    {
     try
     {
     loadNumbers ();
     }
     catch(IOException e)
     {
      System.out.println("error info: "+ e);
     }
     numbers.forEach (System.out::println);
    }
    
    private void loadNumbers() throws IOException
    {
       numbers.clear ();
        
        BufferedReader in = new BufferedReader (new FileReader (fname));
        String line = in.readLine();
        while (line != null) {
            line = line.trim ();
            String[] field = line.split(",");           
            String value = field[0];
            numbers.add (value);
            line = in.readLine();
            }
        in.close ();
    }
    
    public static void main(String[] args) 
    {
     new Stack().run();   
    }
    
}
