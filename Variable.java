class Variable
{
	//static variable
	static int i=10;
		
	public static void main(String[] args)
	{
		//we can call directly also
		System.out.println(i);  
	
		//we can call by using the class name
		System.out.println(Variable.i);
		
		//creating the object reference
		Variable v=new Variable();
		System.out.println(v.i);
	}
}