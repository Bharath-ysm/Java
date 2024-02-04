public class Repeated
{
	public static void main(String[] args) 
	{
		int[] arr={1,4,6,9,5,1,3,3,6,6,7,6,4,1,6};
		
		int cnt=0;
		
		int rept=0;
		
		for(int i=0;i<arr.length;i++)
		{
		    for(int j=i+1;j<arr.length;j++)
		    {
		        if(arr[i]==arr[j])
		        {
		            rept=arr[i];
		            cnt++;
		            continue;
		        }
		    }
		    
		}
		
		System.out.println("most repeated element in the array is:"+rept);
	}
}
