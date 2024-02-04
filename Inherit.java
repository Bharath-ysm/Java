class Parent
{
    public void m1()
    {
        System.out.println("method is m1");
    }
}
class Child extends Parent
{
    public void m2()
    {
        System.out.println("method is m2");
    }
}
class Inherit
{
    public static void main(String[] args)
    {
        Parent p=new Parent();
        p.m1();
        
        Child c=new Child();
        c.m1();
        c.m2();
        
        //parent can hold the child Object
        Parent p1=new Child();
        p1.m1();
    }
}