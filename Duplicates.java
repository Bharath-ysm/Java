public class Duplicates
{
	public static void main(String[] args) 
	{
	   //print the duplicate elements
	   int[] arr={2,5,7,1,3,6,6,5,9};	
	   
	   for(int i=0;i<arr.length;i++)
	   {
	       for(int j=i+1;j<arr.length;j++)
	       {
	           if(arr[i]==arr[j])
	           {
	               System.out.print(arr[i]+" ");
	           }
	       }
	   }
	}
}
