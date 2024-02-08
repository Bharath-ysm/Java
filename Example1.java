import java.util.*;
import java.util.stream.*;
class Example1 
{
    public static void main(String[] args)
    {
        List<Integer> l=Arrays.asList(5,2,8,1,3,9,7);
        
        //odd numbers
        List<Integer> list=l.stream().filter(i->i%2!=0).collect(Collectors.toList());
        
        System.out.println(list);
    }
}