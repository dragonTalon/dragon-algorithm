package com.dragon.talon.algorithm.solution;

import java.util.Arrays;

/**
 * @ClassName MissingNumberS
 * @Version 1.0
 * @Author dragon
 * @Date 2021/8/29 3:39 下午
 * @Description: 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 * example：
 * 输入：[3,0,1]
 * 输出：2
 **/
public class MissingNumberS {

    public static void main(String[] args) {
        final int result = new MissingNumberS().missingNumberOther(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});
        System.out.println(result);
    }

    /**
     * 空间还时间
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int[] arr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]] = 1;
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (arr[i] == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 排序 遍历
     *
     * @param nums
     * @return
     */
    public int missingNumberOther(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < (nums.length + 1); i++) {
            if (i == nums.length) {
                return i;
            }
            if (nums[i] != i) {
                return i;
            }
        }
        return 0;
    }

    private void quickSort(int[] arr, int low, int hight) {
        if (low >= hight) {
            return;
        }
        final int position = position(arr, low, hight);
        quickSort(arr, low, position - 1);
        quickSort(arr, position + 1, hight);

    }

    private int position(int[] arr, int low, int hight) {
        int i = low;
        int j = hight + 1;
        int position = arr[low];
        while (true) {
            while (arr[++i] < position) {
                if (i == hight) {
                    break;
                }
            }
            while (arr[--j] > position) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
