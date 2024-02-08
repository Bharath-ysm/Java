import java.util.*;
import java.util.stream.*;
class Do
{
    public static void main(String[] args)
    {
        List<Integer> l=Arrays.asList(5,2,8,1,3,9,7);
        
        //descending order of the List
        List<Integer> list=l.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        
        System.out.println(list);
    }
}