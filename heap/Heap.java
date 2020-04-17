/*
Implementing a Heap 
Haoran Cheng
hc633
 */
package heap;

import java.util.*;
import java.io.*;

public class Heap 
{
   static int size = 500;
   static int index =0;
   static int[] array = new int[size];
  public static void main(String[] args)
  {
 
     String fname = "numbers.txt";
    try
     {
        BufferedReader in = new BufferedReader (new FileReader (fname));
        String line = in.readLine();

        int c = 0;
        while(line !=null)
        { 
         int a =Integer.parseInt(line.trim());
          
         minheap(a);
       
         line = in.readLine();
         }
        in.close();
     }
    
    catch(IOException e)
    {
         System.out.println("error info: " + e);
    }


    System.out.println("minheap");
    for(int i = 0; i< 5;i++)
    {
     System.out.println(array[i]);
    }
    System.out.println("maxheap");
  
  makeheap();

    for(int i = 0; i< 15;i++)
    {
   System.out.println(array[i]);
    }      
}
 public static void minheap(int value)
 {
  array[index] = value;
  siftup(index);
  index++;
 }
 
 public static void siftup(int index)
 {
     int p;
     if(index==0)
     return;
     
     if(index%2 ==0)
     p = index/2 -1;
     
     else
     p = (index -1)/2;
     
     if(array[p]>array[index])
     swap(p,index);
     siftup(p);
 }

public static void makeheap()
{
 for(int i = (size-3)/2; i >=0; i--)
 {
   siftdown(i);
 }
} 
 
public static void siftdown(int index)
{
  int c = index*2 +1;

  if(array[c] < array[c+1])
  {
    c = c + 1;
  }

  if(array[index] < array[c])
  {
     swap(index,c);
    siftdown(c);
  } 
} 

 public static void swap(int i, int p)
 {
  int a = array[i];
  array[i] = array[p];
  array[p] = a;
 }
}
    

