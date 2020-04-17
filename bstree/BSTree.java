/*
BST sort 
Haoran Cheng
hc633
 */
package bstree;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BSTree{

      BSTree leftnode;
      BSTree rightnode;
      int value;
    
    public void insert(int v)
    {
        //if nothing in root, then set the first intput value as root
      if(value == 0)
         value = v;
     else
     {
       //if input value <= parent go ahead
       if( v <= value)
       {
          //nothing in left, ok , put the value in left
          if(leftnode ==null)
            //create child nodes for left
           leftnode = new BSTree();
           leftnode.insert(v);
       }
       //if input value > parent go ahead
       else{
           //nothing in left, ok , put the value in right
        if(rightnode ==null)
            //create child nodes for right
          rightnode = new BSTree();
          rightnode.insert(v);
        }
      }     
    }
    
    public List<Integer> sort()
    {
        //create a list to store the sorting results
        List<Integer> numbers = new ArrayList<>();
        
        //node has value, add into array
       if(leftnode != null)
            numbers.addAll(leftnode.sort());
       
            numbers.add(value);
       
        if(rightnode !=null)
           numbers.addAll(rightnode.sort());
           return numbers;
        
       
   }
    
          
    public static void main(String[] args)
    {
        BSTree node = new BSTree();
        String file = "numbers.txt";
        System.out.println("file name: " + file);
        
        //input local file
        try
        {
         BufferedReader in = new BufferedReader(new FileReader(file));
         String line = in.readLine();
         
         while(line != null)
         {
           //convert the values into integer and input into tree
            node.insert(Integer.parseInt(line.trim()));
            line = in.readLine();
         }
         in.close();
        }
        //catch errors
        catch(IOException e)
        {
         System.out.print("erro info: "+e);
        }

 //print sort results
for(int i = 0; i < node.sort().size();i++)
 {
   System.out.printf("%5d",node.sort().get(i));
  
   if((i+1)%10 == 0)
   System.out.println();
 }
    }

}