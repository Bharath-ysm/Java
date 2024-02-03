import java.util.Arrays;
public class Combine
{
	public static void main(String[] args) 
	{
	   int[] arr1={5,9,1,4,2};
	   int[] arr2={3,8,7,6,10};
        
       int size1=arr1.length;
       int size2=arr2.length;
       
       arr1=Arrays.copyOf(arr1,size1+size2);
       
       int j=0;
       
       for(int i=size1;i<arr1.length;i++)
       {
           arr1[i]=arr2[j++];
       }
       
       Arrays.sort(arr1);
       
       //for each loop 
       for(int i:arr1)
       {
           System.out.print(i+" ");
       }
	}
}
