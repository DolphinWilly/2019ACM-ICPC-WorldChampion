import java.util.*;
/*
 *  a classic problem to use Binary Search
 *  Binary Search the position that satisfies a set of condition
 *  as long as the position satisfies a monotonic change, we can use Binary Search
 *  
 *  Tech Trivial learned: when doing a binary search, be sure to keep track of a low and high where the search pointer will never
 *  go beyond the low or high (low or high can be either inclusive or exclusive for your own convenience)
 *  
 *  Online Judge: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */
public class CombMedian {
	public static void main(String args[]) {
		int[] nums1 = {1,2,3,6,7};
		int[] nums2 = {4,5,8,9,10};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int l1 = nums1.length;
		int l2 = nums2.length;
		if(l1 > l2) {
			return findMedianSortedArrays(nums2, nums1);
		}
		// can assume l1 <= l2 now
		// now we binary search on position in nums1
		// how many do you want to assign to the smaller side
		int low_k1 = -1;
		int high_k1 = l1+1;
		int k1 = (low_k1 + high_k1)/2;
		int k2 = (l1+l2)/2 -k1;
		while(true) {
//			System.out.println("k1 "+k1+" k2 " +k2);
			if (k1 > 0 && nums2[k2] < nums1[k1-1]) {
				high_k1 = k1;
				k1 = (low_k1 + high_k1)/2;
				k2 = (l1+l2)/2 - k1;
				continue;
			}
			
			if(k1 < l1 && nums1[k1] < nums2[k2-1]) {
				low_k1 = k1;
				k1 = (low_k1 + high_k1)/2;
				k2 = (l1+l2)/2 - k1;
				continue;
			}
			break;
		}
		// if break, we successfully find the partition
		if((k1+k2)*2 < l1+l2) { // then we have odd total number
			if(k1 == l1) return nums2[k2];
			return Math.min(nums1[k1], nums2[k2]);
		}
		// now we have even total number
		double low = (k1 == 0) ? nums2[k2-1] : ((k2 == 0) ? nums1[k1-1] : Math.max(nums1[k1-1], nums2[k2-1]));
		double high = (k1 == l1) ? nums2[k2] : ((k2 == l2) ? nums1[k1] : Math.min(nums1[k1], nums2[k2]));
		return (low+high)/2;	
    }
}
