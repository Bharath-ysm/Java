import java.util.LinkedHashSet;
import java.util.Iterator;
public class Distinct
{
	public static void main(String[] args) 
	{
	    //distint elements from the array 
	    int[] arr={2,5,7,1,3,6,6,5,9};	
        
        LinkedHashSet<Integer> lhs=new LinkedHashSet<Integer>();
        
        //for each loop adding the elements from array to linked hash  set by using for each loop 
        for(int i:arr)
        {
            lhs.add(i);
        }
        
        //reading the data from linked hash set
        Iterator itr=lhs.iterator();
        while(itr.hasNext())
        {
            Integer i=(Integer)itr.next();
            System.out.print(i+" ");
        }
    }
}
