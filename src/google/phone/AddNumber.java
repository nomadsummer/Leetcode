package google.phone;

import java.util.ArrayList;
import java.util.List;

public class AddNumber {

	public static List<Integer> sum(int[] nums1, int[] nums2) {
		List<Integer> rst = new ArrayList<Integer>();
		if(nums1.length == 0 && nums2.length == 0) return rst;
		
		
		int idx1 = nums1.length-1, idx2 = nums2.length - 1;
		int carry = 0;
		while(idx1 >= 0 && idx2 >= 0) {
			int sum = (nums1[idx1]+nums2[idx2] + carry)%10;
			carry = (nums1[idx1]+nums2[idx2] + carry)/10;
			rst.add(0, sum);
			idx1--;
			idx2--;
		}
		while(idx1 >= 0) {
			int sum = (nums1[idx1]+ carry)%10;
			carry = (nums1[idx1]+ carry)/10;
			rst.add(0, sum);
			idx1--;
		}
		while(idx2 >= 0) {
			int sum = (nums2[idx2]+ carry)%10;
			carry = (nums2[idx2]+ carry)/10;
			rst.add(0, sum);
			idx2--;
		}
		
		if(carry == 1) rst.add(0,1);
		return rst;
	}
	
	public static int[] addSum(int[] nums1, int[] nums2) {
		List<Integer> rst = new ArrayList<Integer>();
		if(nums1.length < nums2.length) {
			int[] tmp = nums1;
			nums1 = nums2;
			nums2 = tmp;
		}
		
		int idx1 = nums1.length-1, idx2 = nums2.length-1;
		int carry = 0;
		while(idx2 >= 0) {
			int sum = (nums1[idx1]+nums2[idx2] + carry)%10;
			carry = (nums1[idx1]+nums2[idx2] + carry)/10;
			rst.add(0, sum);
			idx1--;
			idx2--;
		}
		while(idx1 >=0) {
			int sum = (nums1[idx1]+ carry)%10;
			carry = (nums1[idx1]+ carry)/10;
			rst.add(0, sum);
			idx1--;
		}
		if(carry == 1) rst.add(0,1);
		int[] res = new int[rst.size()];
		for(int i = 0; i < rst.size(); i++) {
			res[i] = rst.get(i);
		}
		return res;
		
	}
	public static void main(String[] args) {
		int[] nums1 = {1,3,8,9};
		int[] nums2 = {6,7,8};
		List<Integer> rst1 = sum(nums1, nums2);
//		for(Integer i : rst1) {
//			System.out.println(i);
//		}
		int[] rst2 = addSum(nums1, nums2);
		for(int i : rst2) {
			System.out.println(i);
		}
	}
}
