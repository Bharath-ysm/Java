class Car 
{
    public String carName()
    {
        return "Range Rover";
    }
    public String carColor()
    {
        return "black";
    }
    public double carCost()
    {
        return 10000000d;
    }
    public String carOwner()
    {
        return "Charles Spencer";
    }
}
class Details
{
    public void getCarDetails()
    {
        Car c=new Car();
        System.out.println(c.carName());
        System.out.println(c.carColor());
        System.out.println(c.carCost());
        System.out.println(c.carOwner());
    }
}
class Has_A_RelationShip
{
    public static void main(String[] args)
    {
        Details d=new Details();
        
        d.getCarDetails();     
    }
}