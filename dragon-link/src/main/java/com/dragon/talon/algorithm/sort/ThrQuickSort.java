package com.dragon.talon.algorithm.sort;

import java.util.Arrays;

/**
 * 三分向快速排序
 * 一种为了解决在快速排序中含有大量重复元素的情况
 * 其中解决想过会优于归并排序
 *
 * @author dragonboy
 */
public class ThrQuickSort {
    public static <T extends Comparable> void sort(T[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int dw = lo, i = lo + 1, gw = hi;
        T temp = arr[lo];
        int cmp = 0;
        while (i <= gw) {
            cmp = temp.compareTo(arr[i]);
            if (cmp > 0) {
                exch(arr, dw++, i++);
            } else if (cmp < 0) {
                exch(arr, i, gw--);
            } else {
                i++;
            }

        }
        sort(arr, lo, dw-1);//-1的原因是因为dw++实际上是指向下一个待交换的位置
        sort(arr, gw+1, hi);//+1同上
    }

    private static <T extends Comparable> void exch(T[] arr, int lo, int hi) {
        T temp = arr[lo];
        arr[lo] = arr[hi];
        arr[hi] = temp;
    }

    public static void main(String[] args) {
        Character[] characters = new Character[]{'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        sort(characters, 0, characters.length - 1);
        System.out.println(Arrays.toString(characters));
    }
}
