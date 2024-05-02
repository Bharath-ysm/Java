import java.util.Scanner;
class CodeChef
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        int x=sc.nextInt();
        int y=sc.nextInt();

        if(x>=2*y)
        {
            System.out.println("alice is happy");
        }
        else
        {
            System.out.println("bob is sad");
        }
    }
}