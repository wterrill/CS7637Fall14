package project1;

import java.util.ArrayList;
import java.util.HashMap;

public class Transformation {
    String name1; //name of the first figure x,y,z
    String name2; //name of the second figure x,y,z, etc
    ArrayList<TransAttribute> attributes1;  //list of the attributes for first figure
    ArrayList<TransAttribute> attributes2;  //list of the attributes for second figure
    //ArrayList<TransAttribute> same; //array that tells when

    
    

    public void figureTransformation(RavensObject object1, RavensObject object2) 
    {
        this.name1 = object1.getName();
        this.name2 = object2.getName();
        //this.attributes
        
    }


    public String getFrom() 
    {
        return name1;
    }
    
    public String getTo() 
    {
        return name1;
    }

    public ArrayList<TransAttribute> getAttributeFrom() 
    {
        return attributes1;
    }
    
    public ArrayList<TransAttribute> getAttributeto() 
    {
        return attributes2;
    }
    
    public void setDifferences(RavensObject object1, RavensObject object2)
    {
    	
    }
}
