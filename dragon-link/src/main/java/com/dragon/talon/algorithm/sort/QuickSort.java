package com.dragon.talon.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 快速排序是一个数据为N的数组的排序时间与NlogN成正比
 * 缺点：
 * 非常脆弱，很容易影响到性能
 *
 * @author dragonboy
 */
public class QuickSort {
    public static <T extends Comparable> void sort(T[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        //找到一个 中间值 前面的所有都比自己小，后面的都比自己大
        int position = position(arr, lo, hi);
        sort(arr, lo, position - 1);
        sort(arr, position + 1, hi);
    }

    public static <T extends Comparable> int position(T[] arr, int lo, int hi) {
        T position = arr[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (arr[++i].compareTo(position) < 0) {
                if (i == hi) {
                    break;
                }
            }
            while (arr[--j].compareTo(position) > 0) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
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
