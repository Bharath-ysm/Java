public class Unique
{
	public static void main(String[] args) 
	{
	  //unique elements
	  int[] arr={2,5,7,1,3,6,6,5,9};
	  
	 
	  
	  for(int i=0;i<arr.length;i++)
	  {
	      
	      int cnt=0;
	      
	      for(int j=0;j<arr.length;j++)
	      {
	          if(arr[i]==arr[j])
	          {
	              cnt++;
	          }
	      }
	      if(cnt==1)
	      {
	          System.out.print(arr[i]+" ");
	      }
	  }
	}
}
