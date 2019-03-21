package com.dragon.talon.algorithm.sort;

import java.util.Arrays;

/**
 * 标准的堆排序
 *      如果需要升序，那么在沉的是有应找最大值
 *      反之，降序找最小值
 *      堆排序其实相对于希尔排序、快排、归并排序来是一般的，
 *      但是相对于大数据里面找最小值或者最大值，堆排序首选。
 * @author dragonboy
 */
public class StandardHeapSort {
    public static <T extends Comparable> void sort(T[] arr) {
        int length = arr.length;
        for (int i = length / 2; i >= 0; i--) {
            sink(arr, i, length - 1);
        }
        int index = length - 1;
        while (index>0){
            exch(arr,0,index--);
            sink(arr,0,index);
        }
    }

    private static <T extends Comparable> void sink(T[] arr, int start, int end) {
        int index;
        while (((start + 1) * 2 - 1) <= end) {
            index = (start + 1) * 2 - 1;
            if ((index + 1) <= end && compare(arr, index, index + 1)) {
                index = index + 1;
            }
            if (compare(arr, index, start)) {
                break;
            }
            exch(arr, index, start);
            start = index;
        }
    }

    private static <T extends Comparable> void exch(T[] arr, int lo, int hi) {
        T temp = arr[lo];
        arr[lo] = arr[hi];
        arr[hi] = temp;
    }

    private static <T extends Comparable> boolean compare(T[] arr, int lo, int hi) {
        return arr[lo].compareTo(arr[hi]) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{2, 7, 1, 4, 9, 22, 66, 33, 99};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
