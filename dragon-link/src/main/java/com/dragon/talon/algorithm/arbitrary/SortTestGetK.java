package com.dragon.talon.algorithm.arbitrary;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SortTestGetK
 * @Version 1.0
 * @Author dragon
 * @Date 2021/8/9 11:21 下午
 * @Description
 **/
public class SortTestGetK {
    public static void main(String[] args) {
        int[] a = new int[]{3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(a, k));

    }

    public static int findKthLargest(int[] nums, int k) {
        sort(nums);
//        System.out.println(Arrays.toString(nums));
//        Set<Integer> set = new TreeSet();
//        for (int i = 0; i < nums.length; i++) {
//            set.add(nums[i]);
//        }
//        final Iterator<Integer> iterator = set.iterator();
//        int a = 1;
//        int max = 0;
//        while (iterator.hasNext()) {
//            final Integer next = iterator.next();
//            if (a % k == 0) {
//                max = max < next ? next : max;
//            }
//            a++;
//        }
        return nums[nums.length-k];
    }

    public static void sort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && nums[j - 1] > nums[j]; j--) {
                int temp = nums[j - 1];
                nums[j - 1] = nums[j];
                nums[j] = temp;
            }
        }
    }
}
