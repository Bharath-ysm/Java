import java.util.*;
import java.util.stream.*;
class GraceMarks
{
    public static void main(String[] args)
    {
        List<Integer> l=Arrays.asList(5,2,8,1,3,9,7);
        
        //adding 10 grace marks out of 25
        List<Integer> marks=l.stream().map(i->i+10).collect(Collectors.toList());
        
        System.out.println(marks);
    }
}