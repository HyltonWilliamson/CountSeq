
/*Hylton Williamson
 * ID: 3239463
 * Prof. Arup Guha
 * COP 3503 Spring 2018
 * Program Name: Count Seq
 *
 * This program counts how many unique times a sub sequence can be made
 * from a bigger sequence.
 */


import java.util.*;

public class countseq{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int loops = input.nextInt();
		String seq, sub;

		for (int i = 0; i < loops; i++)
		{
			seq = input.next();
			sub = input.next();
			int seqlen = seq.length();
			int sublen = sub.length();
			
			//Handles the base case if the sub sequence is only 1 character.
			if(sublen == 1)
			{
				System.out.println(countSimple(sub.charAt(0), seq, seqlen));
			}else if (!(seq.equals(sub)))
			{//Checks if the sequence and the sub sequence are equal, if so, return 1.
				solve(seq,sub, seqlen, sublen);
			}else
			{
				System.out.println(1);
			}
		}
		input.close();
	}
	
	public static void solve(String seq, String sub, int seqlen, int sublen)
	{
			//Makes an array to count show paths to each index.
			long[] starter = new long[seq.length()];
			for (int t = 0; t < seqlen; t++)
				starter[t] = 1;

			for (int j = sublen - 2; j >= 0; j--)
			{
				// Letter to be searched for
				char letter = sub.charAt(j);
				// Find the positions letter in the array
				long[] seqArr = findStarts(seq, letter, seqlen);
				// Letter to be found
				char target = sub.charAt(j + 1);
				for (int k = 0; k < seqlen; k++)
				{
					if (seqArr[k] == 1)
					{
						// Find how many instances of "search" occur after k and
						//puts the sum of the value they hold in
						seqArr[k] = countChar(target, seq, k, starter, seqlen);
					}
				}
				//Updates the starting compare array
				starter = seqArr;
			}
			//Counts the values of the array to get total paths
			long result = 0;
			for (int y = 0; y < seqlen; y++) {
				result += starter[y];
			}
			System.out.println(result);
	}

	//Marks the indexes at which the current char in the sub sequence are located.
	public static long[] findStarts(String seq, char subSeq, int seqlen)
	{
		long[] strtPoints = new long[seqlen];
		for (int i = 0; i < seqlen; i++)
		{
			if (seq.charAt(i) == subSeq)
				strtPoints[i] = 1;
		}

		return strtPoints;
	}

	// Counts how many times a char appears after a starting index
	public static long countChar(char target, String seq, int start, long[] seqArr, int seqlen)
	{
		long result = 0;
		for (int i = start + 1; i < seqlen; i++)
		{
			if (seq.charAt(i) == target)
				result += seqArr[i];
		}
		
		return result;
	}
	//Method to handle the base case of the sub sequence being 1 character.
	public static long countSimple(char target, String seq, int seqlen){
		long result = 0;
		for(int i = 0; i < seqlen; i++)
		{
			if(target == seq.charAt(i))
				result++;
		}
		return result;
	}
	
}
