package com.rr.jprog_interface.programs;

import java.util.Arrays;

public class MergeSortedArrays {
    /**
     * SOLUTION 1: Three Pointers from the End
     *
     * Core Insight: Merge from the end to avoid overwriting elements in nums1
     *
     * Time Complexity: O(m + n) - single pass through both arrays
     * Space Complexity: O(1) - in-place modification
     *
     * @param nums1 First sorted array with extra space at the end
     * @param m Number of valid elements in nums1
     * @param nums2 Second sorted array
     * @param n Number of elements in nums2
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize 2 Pointers
        int pointer1 = m - 1; // Last valid element in nums1
        int pointer2 = n - 1; // Last element in nums2
        int mergePosition = m + n - 1; // Last position in nums1

        // Merge while both arrays have elements
        while (pointer1 >= 0 && pointer2 >= 0) {
            if(nums1[pointer1] > nums2[pointer2]) {
                // nums1 has larger element, move it to the end
                nums1[mergePosition] = nums1[pointer1];
                pointer1--;
            } else {
                // nums2 has larger or equal element, move it to the end
                nums1[mergePosition] = nums2[pointer2];
                pointer2--;
            }
            mergePosition--;
        }

        // If there are remaining elements in nums2, copy them
        // No need to copy remaining nums1 elements as they're already in place
        while (pointer2 >= 0) {
            nums1[mergePosition] = nums2[pointer2];
            pointer2--;
            mergePosition--;
        }
    }

    /**
     * SOLUTION 2: Alternative using System.arraycopy
     *
     * Same time complexity but uses built-in array copy for remaining elements
     */
    public static void mergeWithArryCopy (int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for nums1
        int j = n - 1; // Pointer for nums2
        int k = m + n - 1; // Pointer for merge position

        // Merge from the end
        while (i >= 0 && j >= 0) {
            nums1[k--] = (nums1[i] > nums2[j] ? nums1[i--] : nums2[j--]);
        }

        // If nums2 has remaining elements, copy them to beginning of nums1
        if (j >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }
    }
}
