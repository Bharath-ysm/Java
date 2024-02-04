import java.util.Arrays;
public class Add
{
	public static void main(String[] args) 
	{
		int[] arr={6,2,8,1,9,7};
		
		int position=3,element=54;
		
		arr=Arrays.copyOf(arr,arr.length+1);
		
		for(int i=arr.length-2;i>=position;i--)
		{
		    arr[i+1]=arr[i];
		}
		arr[position]=element;
		
		//for each loop 
		for(int i:arr)
		{
		    System.out.print(i+" ");
		}
		
	}
}
