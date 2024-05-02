import java.util.Scanner;
class CodeChef2
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        int i,t,x,a,y;

        System.out.println("enter the number of testcases:");

        t=sc.nextInt();

        for(i=0;i<=t;i++)
        {
            System.out.print("enter  a as chef age:");
            a=sc.nextInt();
            System.out.print("enter x as age limit:");
            x=sc.nextInt();
            System.out.print("enter y as doesnot exceed:");
            y=sc.nextInt();

            if(a>=x && a<y)
            {
                System.out.println("chef age is perfect");
            }
            else
            {
                System.out.println("chef age is not perfect");
            }
        }
    }
}