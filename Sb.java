public class Sb
{
	public static void main(String[] args) 
	{
	   
	   String str="hello";
	   
	   StringBuffer sb=new StringBuffer();
	 
	   for(int i=0;i<str.length();i++)
	   {
	       char c=str.charAt(i);
	       
	       if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u')
	       {
	           sb.append(c);
	       }
	       
	   }
	   System.out.println(sb.toString());
	 
	}
}
