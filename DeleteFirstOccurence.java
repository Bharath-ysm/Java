public class DeleteFirstOccurence
{
	public static void main(String[] args) 
	{
		int[] arr={2,6,9,3,6,8,1,6};
		
		int[] resArr=new int[arr.length];

        int j=0,cnt=0;
        
        int ele=6;
        
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==ele && cnt==0)
            {
                cnt++;
                continue;
            }
            resArr[j++]=arr[i];
        }
        
        //for each loop 
        for(int i:resArr)
        {
            System.out.print(i+" ");
        }
	}
}
