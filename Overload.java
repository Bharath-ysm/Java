//example for the method overloading
class Meeseva
{
    public void search(int voterId)
    {
        System.out.println("Int-arg-method");
    }
    public void search(String houseno)
    {
        System.out.println("String-arg-method");
    }
}
class Overload 
{
    public static void main(String[] args)
    {
        Meeseva ms=new Meeseva();
        
        ms.search(101);
        ms.search("22-4-68");
    }
}