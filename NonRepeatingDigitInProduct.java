import java.util.HashSet;
import java.util.Set;

/**

Given a number 'x', and a range of ‘y’ to ‘z’, please find the count of 
all the numbers ‘n’ in that range, such that the product of the number ‘n’ and 'x' 
does not contain any digit from the number ‘n’.
General Constraints: All the inputs will be integers and below 10

E.g., if x = 2, y = 10 and z = 15, then,

2 x 10 = 20 // Invalid, since the product contains 0 from 10.
2 x 11 = 22 // Valid
2 x 12 = 24 // Invalid, since the product contains 2 from 12.
2 x 13 = 26 // Valid
2 x 14 = 28 // Valid
2 x 15 = 30 // Valid

Hence, the final count is 4.

Example Input:
2
10
15

Example Output:
4

**/

public class NonRepeatingDigitInProduct {
	
	
	public static int nonRepeatingDigitProductCount(int x, int y, int z) {
		
		if( y > z  || x == 1)
			return 0;
		
		int index = y;
		int counter = 0;
		
		while(index <= z) {
			if (validNonRepatingDigitProductCount(x, index)) {
				counter++;
			}
			index++;
		}
	
		return counter;		
	}
		
	
	public static boolean validNonRepatingDigitProductCount(int x, int index) {
		 
		 Set<Integer> set = new HashSet<Integer>();
		 
		 int temp = index;
		 
		 while(temp > 0) {
			 
			 int digit = temp % 10;
			 temp = temp / 10;
			 set.add(digit);
		 }
		 
		 long product = x * index;
		 
		 while(product > 0) {
			 
			 int digit = (int) product % 10;
			 product = product / 10;
			 
			 if(set.contains(digit)) {
				 return false;
			 }
		 }
		 
		 return true;
	 }
}
