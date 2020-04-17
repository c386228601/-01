package karp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Karp {
    
public static void RabinKarpAlogrithm(char[] T,char[] S, int d,int q){
	    int n=T.length;
	    int m=S.length;
	    if( n < m) return ;
	    int h = 1;
	    for(int i=1; i<=m-1; i++)   
	        h = h*d%q;

	    int p=0, t=0;
	    for(int i=0; i<m; i++) {
	        p = (( d*p + S[i]) % q); 
	        t = (( d*t + T[i]) % q);
	    }

	    for(int s=0; s <n-m+1; s++) {  
	    	if( p == t ){
	    		int i=0;
	            for(i=0; i<m; i++)
	                if(S[i]!=T[s+i])
	                    break;
	            if(i==m) System.out.println("Pattern ocurs with shift:"+s);
	        }
	        if( s < n-m )
	            t= (d* (t - T[s]*h%q) + T[s+m])  % q; 
	    }
	    System.out.println("END");
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String strT="";
	String strS="";
                
     String filename = "sources.txt";
      try
      {
          //create buffer start read
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line = in.readLine();
        //receive the reading results
        int count = 0;
        while(line !=null)
        { 
            
            if(count == 0)
                strT = line;
            if(count == 1)
                strS = line;
            count ++;
           
          line = in.readLine();
        }
        in.close();
      }
      catch(IOException e)
      {
          //print errors
            System.out.println("Error information: "+e);
      } 
		char[] T=strT.toCharArray();
		char[] S=strS.toCharArray();
		int d=3;
		int q=13;
		Karp.RabinKarpAlogrithm(T,S,d,q);  

    }
    
}
