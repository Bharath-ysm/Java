import java.util.Scanner;
class Percentage
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter the cgpa:");
		float cgpa=sc.nextFloat();
		
		//logic to convert cgpa to percentage
		float percentage=cgpa*9.5f;
		
		System.out.println("converting cgpa to percentage is:"+percentage);
	}
}