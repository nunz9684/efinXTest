import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexOfInteger {

	public static List<Integer> findIndex(Integer[] integers, int value)
	{
		List<Integer> idx = new ArrayList<>();
		int index = 0;
		int idxCount = 0;
		for(int i : integers)
		{
			System.out.println("integers = " + i);
			if(i == value)
			{
				idx.add(idxCount);
				System.out.println(index);
				index++;
			}
			idxCount++;
		}
		System.out.println(idx.toString());
		return idx;
	}
	public static void main(String[] args)
	{
		Integer[] arr = {1,2,3,4,5,6,9,8,1,5,6,48,7,1,3,5,441,1,3,1,3,1,96,1};  // change array list here
		List<Integer> indexOfArr = new ArrayList<>();
		indexOfArr = findIndex(arr, 96);										// change a value to find index here.
		
		 System.out.println("Index position of 1 is: "
                 + indexOfArr.toString()); 
		 System.out.println("Index position of 1 is: "
                 + Arrays.asList(arr).indexOf(1)); 
	}
}
