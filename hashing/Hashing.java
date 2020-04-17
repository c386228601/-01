package hashing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Hashing 
{
    int count = 0;
   
    int[] values = new int[100];
       
 private final static int TABLE_SIZE = 100;
      LinkedHashEntry[] table;
      Hashing() 
      {
            table = new LinkedHashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++)
                  table[i] = null;
      }
      
      public int get(int key) 
      {
            int hash = (key % TABLE_SIZE);
            if (table[hash] == null)
                  return -1;
            else 
            {
                  LinkedHashEntry entry = table[hash];
                  while (entry != null && entry.getKey() != key)
                        entry = entry.getNext();
                  if (entry == null)
                        return -1;
                  else
                        return entry.getValue();
            }             
      }
      
      public void put(int key, int value) {
            int hash = (key % TABLE_SIZE);
            if (table[hash] == null)
                  table[hash] = new LinkedHashEntry(key, value);
            else 
            {
                  LinkedHashEntry entry = table[hash];
                  while (entry.getNext() != null && entry.getKey() != key)
                        entry = entry.getNext();
                  if (entry.getKey() == key)
                        entry.setValue(value);
                  else
                        entry.setNext(new LinkedHashEntry(key, value));
            }
      }
      
      public void inStream(int v)
      {
       values[count] = v;
       count++;
      }
      
      
       public static void main(String[] args) 
       {
        Hashing h = new Hashing();

        String filename = "numbers.txt";
        try
        {
         BufferedReader in = new BufferedReader(new FileReader(filename));
         String line = in.readLine();
         
         while(line != null)
         {
           //convert the values into integer and input into tree
            h.inStream(Integer.parseInt(line.trim()));
            line = in.readLine();
         }
         in.close();
        }
        //catch errors
        catch(IOException e)
        {
         System.out.print("erro info: "+e);
        }

        
        for(int i = 0; i < h.values.length;i++)
        {
         h.put(h.values[i],h.values[i]%100);
        }
         int countNull = 0;
        for(int i = 0; i < TABLE_SIZE;i++)
        {
           if(h.table[i]==null)
           {
                          countNull++;
           System.out.println(-1);
           }
           else
            System.out.println(h.table[i].getValue());
        }
        
        
                   System.out.println("numbers of empty entry: "+countNull);
}

class LinkedHashEntry 
{
      private int key;
      private int value;
      private LinkedHashEntry next;

      LinkedHashEntry(int key, int value) 
      {
            this.key = key;
            this.value = value;
            this.next = null;
      }
      public int getValue() 
      {
            return value;
      }

      public void setValue(int value) 
      {
            this.value = value;
      }
      
      public int getKey() 
      {
            return key;
      }
      
      public LinkedHashEntry getNext() 
      {
            return next;
      }
      
      public void setNext(LinkedHashEntry next) 
      {
            this.next = next;
      }
 }
}