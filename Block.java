class Block
{
	//instance varibale 
	int i;
	
	//instance block
	{
		i=100;
		System.out.println("instance method");
	}
	public static void main(String[] args)
	{
		//instance block will excecute when we create an object
		Block b=new Block();
		
		System.out.println(b.i);
		
		System.out.println("Main method");
	}
}