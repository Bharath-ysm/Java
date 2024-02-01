import java.util.Scanner;
class Circle
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter the raduis:");
		int r=sc.nextInt();
		
		//logic finding the area of circle
		float area = 3.14f*r*r;
		
		System.out.println("area of a circle is:"+area);
	}
}