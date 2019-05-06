import java.util.ArrayList;
import java.util.List;

public class IndexOfInteger {
	
	private static IndexOfInteger instance;
	public static IndexOfInteger getInstance()
	{
		if( instance == null)
		{
			instance = new IndexOfInteger();
		}
		return instance;
	}
	
	public List<Integer> findIndex(String input, char value)
	{
		List<Integer> idx = new ArrayList<>();
		int len = input.length();
		int index = 0;
		int idxCount = 0;
		
		for(int i = 0; i < len; i++)
		{
			if(input.charAt(i) == value)
			{
				idx.add(idxCount);
//				System.out.println(index);
				index++;
			}
			idxCount++;
		}

		if(index == 0)
		{
			idx.add(-1);
		}
		return idx;
	}
}
