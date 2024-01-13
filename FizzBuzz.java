import java.util.Scanner;
public class FizzBuzz 
{
    public static void main(String[] args) 
    {
        //taking the inputs using the scanner class
        Scanner sc=new Scanner(System.in);

        System.out.println("enter the number");
        int n=sc.nextInt();

        //for loop iterating the numbers for 0 to n
        for(int i=0;i<=n;i++)
        {
            //checks the multiple of 3 and 5
            if(i%3==0 && i%5==0)
            {
                System.out.println("FizzBuzz");
            }
            else if(i%3==0)
            {
                System.out.println("Fizz");
            }
            else if(i%5==0)
            {
                System.out.println("Buzz");
            }
            else
            {
                System.out.println(i);
            }

        }

    }   
}
