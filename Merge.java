class Merge
{
    public static void main(String[] args)
    {
        String[] arr1={"chai","biscuit"};

        String[] arr2={"kitcat","munch","nestle"};

        //calculate the length of the merge array
        int mergedLength=arr1.length+arr2.length;

        //create the new string array by using the length
        String[] res=new String[mergedLength];

        //copy the elements from array1 to res array
        for(int i=0;i<arr1.length;i++)
        {
            res[i]=arr1[i];
        }

        //copy elements from arr2 to res array
        for(int i=0;i<arr2.length;i++)
        {
            res[arr1.length+i]=arr2[i];
        }

        //print the elements of res array
        for(String element:res)
        {
            System.out.print(element+" ");
        }

    }
}