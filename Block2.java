class Block2
{
	//instance block
	{
		System.out.println("instance block");
	}
	//static block
	static
	{
		System.out.println("static block");
	}
	//main method
	public static void main(String[] args)
	{
		Block2 b=new Block2();
		
		System.out.println("hello world");
		
		//static block will executes first because jvm wil find for static 
	}
}