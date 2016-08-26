package google.phone;

import java.util.Arrays;

/*
 * Given a set S of 10^6 doubles. Find a number X so that the [X, X+1) half-open real interval contains as many elements of S as possible.
 * For example, given this subset:[…] 2.7, 0.23, 8.32, 9.65, -6.55, 1.55, 1.98, 7.11, 0.49, 2.75, 2.95, -96.023, 0.14, 8.60, [...]
 * the value X desired is 1.98 because there are 4 values in the range 1.98 to 2.97999 (1.98, 2.7, 2.75, 2.95)
 */
public class DoubleRange {
	public static double getX(double[] nums) {
		
		Arrays.sort(nums);
		int start = 0, end = 1, count = 1;
		double X = nums[0];
		while(start < end && end < nums.length) {
			while(start < end && nums[end] - nums[start] >= 1) {
				++start;
			}
			end++;
			if(end - start + 1 > count) {
				count = end - start + 1;
				X = nums[start];
			}
		}
		return X;
	}
	//incorrect;
	public static int findLongest(double[] nums) {
		
		int start = 0, end = 0, res = 0;
		Arrays.sort(nums);
		while(end < nums.length) {
			
			while(nums[start] + 1 < nums[end]) {
				start++;
			}
			res = Math.max(res, end-start+1);
			end++;
		}
		return res;
	}
	public static void main(String[] args) {
		double[] d1 = {2.7, 0.23, 8.32, 9.65, -6.55, 1.55, 1.98, 7.11, 0.49, 2.75, 2.95, -96.023, 0.14, 8.60};
		double[] d = {-2.0, 1.2, 1.23, 1.29, 3.0};
		System.out.println(getX(d));
		System.out.print(getX(d1));
		
		System.out.print(findLongest(d));
	}
}
/*
public int maxElementsInOne(double[] arr) {
        int n = arr.length;
        // defensive copy
        double[] copy = Arrays.copyOf(arr, n);
        Arrays.sort(copy);
        int ans = 0, i = 0;
        for (int j = 0; j < n; ++j) {
            if (copy[j] < copy[i] + 1) continue;
            ans = Math.max(ans, j - i++);
        }.
        return Math.max(ans, n - i);
    } 
 */
