//explicit class import ---> improves the readabilty of our code
import  java.time.LocalDate;
import  java.time.LocalTime;
class Statement
{
	public static void main(String[] args) 
	{
		LocalDate date=LocalDate.now();
		System.out.println(date);

		LocalTime time=LocalTime.now();
		System.out.println(time);
	}
}
