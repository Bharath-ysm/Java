public class SmallestElement
{
	public static void main(String[] args) 
	{
	   int[] arr={5,8,1,3,9,4};
	   
	   int small=arr[0];
	   
	   for(int i=0;i<arr.length;i++)
	   {
	       if(arr[i]<small)
	       {
	           small=arr[i];
	       }
	   }
	   
	   System.out.println("big element in the array is :"+small);
	 
	}
}
