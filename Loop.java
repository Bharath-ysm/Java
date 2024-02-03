class Loop
{
    public static void main(String[] args)
    {
     
        //Ascending rows
		for(int i=1;i<=4;i++)
		{
			//space
			for(int j=4;j>i;j--)
			{
				System.out.print("  ");
			}
			//left side elements
			for(int j=1;j<=i;j++)
			{
				System.out.print(j+" ");
			}
			//right side elements
			for(int j=i-1;j>=1;j--)
			{
				System.out.print(j+" ");
			}
			//new line 
			System.out.println("");
		}
		//descending rows 
		for(int i=3;i>=1;i--)
		{
		    //space
		    for(int j=4;j>i;j--)
		    {
		        System.out.print(" ");
		    }
		    //left side 
		    for(int j=1;j<=i;j++)
		    {
		        System.out.print(j+" ");
		    }
		    //right side
		    for(int j=i-1;j>=1;j--)
		    {
		        System.out.print(j+" ");
		    }
		    //new line 
		    System.out.println("");
		}
    }
}