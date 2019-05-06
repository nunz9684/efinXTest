import java.util.List;
import java.util.Scanner;
import java.lang.Math; 

public class Decompress {
	
	public static void textProcess(String input)
	{
		int level = 0;
		List<Integer> bracketCnt = IndexOfInteger.getInstance().findIndex(input, '[');
		int head = 0;
		int tail = 0;
		String buff = input;
		String tempBuff = input;
		int i = 0;
		while(true)
		{	
			level = findLevel(buff);
			if(level != 1)
			{
				buff = stringCut(buff);
				i++;
				continue;
			}
			else
			{
				textProc(buff);
				head = bracketCnt.get((i - 1 < 0 ) ? 0 : i-1) ;
				tail = head + buff.length() + 1;
				buff = mergeString(head, tail, tempBuff, textProc(buff));
				tempBuff = buff;
				bracketCnt = IndexOfInteger.getInstance().findIndex(tempBuff, '[');
				i = 0;
			}
			if(bracketCnt.get(0) == -1)
			{
				break;
			}
		}
		System.out.println("Output : " + tempBuff);
	}
	
	public static String mergeString(int h, int t, String mainStr, String subStr)
	{
		String result = "";
		String headBuff = "";
		String tailBuff = "";
		
		for(int i = 0 ; i <= h ; i++)
		{
			headBuff += mainStr.charAt(i);
		}
		
		for(int i = t ; i < mainStr.length() ; i++)
		{
			tailBuff += mainStr.charAt(i);
		}
		
		result = headBuff + subStr + tailBuff;
		if(tailBuff == "")
		{
			return subStr;
		}
		return result ;
	}
	
	public static String stringCut(String input)
	{
//		String buff = input;
		int openBracketCnt = 0;
		int closeBracketCnt = 0;
		int startBracket = 0 ;
		int endBracket = 0;
		int state = 0 ;
		String result = "";
		int i = 0;
		while(true)
		{
			if(input.charAt(i) == '[')
			{
				if(state == 0)
				{
					startBracket = i;
					state = 1;
				}
				openBracketCnt++;
			}
			if(input.charAt(i) == ']')
			{
				closeBracketCnt++;
			}
			if((openBracketCnt - closeBracketCnt == 0) &&
					openBracketCnt != 0 && closeBracketCnt != 0)
			{
				endBracket = i;
				break;
			}
			i++;
		}
		
		for(int j = startBracket + 1; j < endBracket ; j++)
		{
			result += input.charAt(j);
		}
		return result;
	}
	
	public static String textProc(String input)
	{
		String result = "";
		IndexOfInteger idxOfInterger = new IndexOfInteger();
		int times = idxOfInterger.findIndex(input, '[').size();
		int i = 0;
		int state = 0;
		String buff = "";
		String workBuff = "";
		for(int j = times; j > 0; j--)
		{
			while(true)
			{
				if(i >= input.length())
				{
					break;
				}
				workBuff += input.charAt(i);
				if(state == 1 && input.charAt(i) == ']')
				{
					break;
				}	
				
				if(input.charAt(i) == '[' && state == 0)
				{
					state = 1;
					i++;
					continue;
				}
				if(state == 1 && input.charAt(i) != ']' )
				{
					buff += input.charAt(i);
					i++;
					continue;
				}
				
				i++;
			}
			state = 0;

			result += makeText(findPower(workBuff), buff);
			for(int k = i ; k < input.length(); k++ )
			{
				if(input.charAt(k) <= '9' && input.charAt(k) >= '0')
				{
					break;
				}
				if(input.charAt(k) <= 'z' && input.charAt(k) >= 'a' ||
				  input.charAt(k) <= 'Z' && input.charAt(k) >= 'A'	)
				{
					result += input.charAt(k);
				}	
			}
			workBuff = "";
			buff = "";
		}

		return result;
	}
	
	public static String makeText(int pow, String input)
	{
		String result = "";
		for(int i = pow ; i > 0 ; i--)
		{
			result += input;
		}
		return result;
	}
	public static int findPower(String input)
	{
		String buff = "";
		if(input.length() == 0)
		{
			return 0;
		}
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == '[')
			{
				break;
			}
			if(input.charAt(i) <= '9' && input.charAt(i) >= '0')
			{
				buff += input.charAt(i);
			}
		}
		
		return strToint(buff);
	}
	public static int strToint(String input)
	{
		int mul = (int) Math.pow(10, input.length() - 1 );
		int result = 0;
		int i = 0 ;
		while(true)
		{
			if(i == input.length())
			{
				break;
			}
			if(mul < 1)
			{
				break;
			}
			result += ((input.charAt(i) - '0') * mul);
			i++;
			mul /= 10;
			
		}
		return result;
	}
	
	
	public static int findLevel(String input)
	{
		int result = 0;
		char prevState = '[';
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == '[')
			{
				if(prevState == '[')
				{
					result++;
				}
			}
			if(input.charAt(i) == '[' || input.charAt(i) == ']')
			{
				prevState = input.charAt(i);
			}
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		while(true)
		{
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			System.out.print("intput : ");
			String input = myObj.nextLine();
			
			textProcess(input);
			myObj.close();
		}
		
	}
}
