class Student
{
    //encapsulation example
    
    //pojo class 
    private int studId;
    private String studName;
    private String studAdd;
    
    //setter methods
    public void setStudId(int studId)
    {
        this.studId=studId;
    }
     public void setStudName(String studName)
    {
        this.studName=studName;
    }
     public void setStudAdd(String studAdd)
    {
        this.studAdd=studAdd;
    }
    
    //getter methods
    public int getStudId()
    {
        return studId;
    }
     public String getStudName()
    {
        return studName;
    }
     public String getStudAdd()
    {
        return studAdd;
    }
    
	public static void main(String[] args) 
	{
	   
	   Student s=new Student();
	   
	   s.setStudId(101);
	   s.setStudName("Alan");
	   s.setStudAdd("Germany");
	   
	   System.out.println(s.getStudId());
	   System.out.println(s.getStudName());
	   System.out.println(s.getStudAdd());
	   
	}
}
