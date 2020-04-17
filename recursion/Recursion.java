/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;
import java.util.*;
import java.util.function.Function;

public class Recursion {
    
    public static void main(String[] args) 
    {
        Node a = new Node ("A");
        Node b = new Node ("B");
        Node c = new Node ("C");
        Node d = new Node ("d", 10);
        Node e = new Node ("e", 15);
        Node f = new Node ("f", 15);
        Node g = new Node ("g", 25);
        Node h = new Node ("h", 10);
       Node w = new Node ("h", 10);
        
        a.addChild (b);
        a.addChild (c);
        a.addChild (d);
        
        b.addChild (e);
        b.addChild (f);
        
        c.addChild (g);
        c.addChild (h);
        c.addChild (w);
        
//      d.addChild (a);
//      a.printTree ();

//    System.out.println ("Tree count for " + a.label + " is: " + a.countTree ());
//    System.out.println ("Tree value for " + a.label + " is: " + a.valueTree ());
/*        
        a.printTree ();
        a.map (x->x+1);
        a.printTree ();

        a.printTree ();
        a.mod (n -> { n.mod1 (); });
        a.mod (Node::mod1);
        a.printTree ();
*/

        List<Node> rList = a.leaves ();
        rList.forEach (System.out::println);
    }
}


class Node
{
    String label;
    private double value;
    private List<Node> children;
    
    public Node (String label)
    {
        this.label = label;
        this.value = 0;
        this.children = new ArrayList<Node>();
    }
    
    public Node (String label, double value)
    {
        this.label = label;
        this.value = value;
        this.children = new ArrayList<Node>();
    }
    
    public List<Node> leaves ()
    {
        List<Node> result = new ArrayList<Node>();
        leaves_(result);
        return result;
    }
    
    private void leaves_ (List<Node> result)
    {
        if (children.size () == 0)
            result.add (this);
        else {  
            for (Node child : children)
                child.leaves_ (result);
            }         
    }
    
    public List<Node> leaves1 ()
    {
        List<Node> result = new ArrayList<Node>();
        if (children.size () == 0)
            result.add (this);
        else {  
            for (Node child : children)
                result.addAll (child.leaves1 ());            
            } 
        
        return result;
    }
    
    public void mod1 ()
    {
        label += "_1";
    }
    
    public void mod (ModifyNodeFunction f)
    {
       f.apply (this);       
       for (Node child : children)
           child.mod (f);  
    }
    
    public void map (Function<Double,Double> f)
    {
       if (children.size () == 0) {
           this.value = f.apply(this.value);
           return;
           }
       
       for (Node child : children)
           child.map (f);  
    }
    
    public void addChild (Node child)
    {
        children.add (child);
    }
    
    public String toString ()
    {
        return label + " " + value;
    }
    
    public void printTree ()
    {
        System.out.println (this);
        for (Node child : children)
            child.printTree ();
    }
    
    public int countTree ()
    {
       if (children.size () == 0) 
           return 1;
       
       int total = 1;
       for (Node child : children)
           total += child.countTree ();
       return total;
    }
   
    public double valueTree ()
    {
       if (children.size () == 0) 
           return this.value;
       
       double total = 0;
       for (Node child : children)
           total += child.valueTree ();
       return total;
    }
}

interface ModifyNodeFunction
{
    void apply (Node n);
}