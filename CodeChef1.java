import java.util.Scanner;
class CodeChef1
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("enter the a:");
        int a =sc.nextInt();

        System.out.println("enter the value as b:");
        int b=sc.nextInt();

        if(a+b+(a*b)==111)
        {
            System.out.println("yes");
        }
        else
        {
            System.out.println("no");
        }
    }
}