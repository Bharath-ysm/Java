class Block1
{
	//static variable
	static int i;
	
	//static block
	static
	{
		i=100;
		System.out.println("static block");
	}
	public static void main(String[] args)
	{
		//static variables we can call direclty or by using class name or creating object reference
		System.out.println(i);
		
		System.out.println("main method");
	}
}