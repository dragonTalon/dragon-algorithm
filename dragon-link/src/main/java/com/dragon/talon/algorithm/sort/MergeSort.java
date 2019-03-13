package com.dragon.talon.algorithm.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

/**
 * 归并排序：
 * 一种将数组分割然后 分别排序最后在合并的方式
 * 优点：在时间上趋近与 NlogN
 * 缺点：比较好内存，空间不友好
 *
 * @author dragonboy
 */
public class MergeSort {
    /**
     * 原地归并排序
     *
     * @param arr 数组
     * @param lo  低位
     * @param mid 中间位置
     * @param hi  高位
     * @param <T> 要做比较呀～
     */
    public static <T extends Comparable> void mergeSortInPlace(T[] arr, int lo, int mid, int hi) {
        if (arr.length - (hi - lo) < 0) {
            throw new IllegalArgumentException("Optional length more than array length");
        }
        int i = lo, j = mid + 1;
        T[] temp = (T[]) new Comparable[(hi - lo + 1)];
        for (int k = lo; k <= hi; k++) {
            temp[k - lo] = arr[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = temp[j - lo];
                j++;
            } else if (j > hi) {
                arr[k] = temp[i - lo];
                i++;
            } else if (temp[i - lo].compareTo(temp[j - lo]) > 0) {
                arr[k] = temp[j - lo];
                j++;
            } else {
                arr[k] = temp[i - lo];
                i++;
            }
        }

    }

    public static <T extends Comparable> void insertSort(T[] arr, int begin, int end) {
        if (arr.length < end) {
            throw new IllegalArgumentException("Optional length more than array length");
        }
       
        for (int i = begin + 1; i <= end; i++) {
            for (int j = i; j > begin && arr[j - 1].compareTo(arr[j]) > 0; j--) {
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    /**
     * 从顶向下
     *
     * @param arr
     * @param begin
     * @param end
     * @param <T>
     */
    public static <T extends Comparable> void binarySort(T[] arr, int begin, int end) {
        int mid = (end - begin) / 2 + begin;
        if (end - begin <= 1 && arr[mid + 1].compareTo(arr[mid]) > 0) {
            return;
        }
        if (end - begin > 1) {
            binarySort(arr, begin, mid);
            binarySort(arr, mid + 1, end);
        }
        insertSort(arr, begin, mid);
        insertSort(arr, mid + 1, end);
        mergeSortInPlace(arr, begin, mid, end);

    }

    /**
     * 从低向上
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable> void lowToHightSort(T[] arr) {
        int length = arr.length;
        for (int i = 1; i < length - 1; i += i) {
            for (int j = 0; j < length - i; j += i + i) {
                mergeSortInPlace(arr, j, j + i - 1, Math.min(length, j + i + i - 1));
            }
        }
    }


    public static void main(String[] args) {
        Character[] arr = new Character[]{'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        //最普通的归并排序  先分开排序
        int lo = 0;
        int hi = arr.length - 1;
        int mid = lo + (hi - lo) / 2;
        long begin = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        insertSort(arr, lo, mid);
        System.out.println("左边排序结果 ：" + Arrays.toString(arr));
        insertSort(arr, mid + 1, hi);
        System.out.println("右边排序结果 ：" + Arrays.toString(arr));
        mergeSortInPlace(arr, lo, mid, hi);
        System.out.println("最后的结果 ：" + Arrays.toString(arr));
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("普通的时间 ：（毫秒）" + (end - begin));
        Character[] characters = new Character[]{'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        /**
         * 自顶向下 的归并排序
         */
        lo = 0;
        hi = characters.length - 1;
        mid = lo + (hi - lo) / 2;
        begin = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        binarySort(characters, lo, mid);
        System.out.println("左边排序结果 ：" + Arrays.toString(characters));
        binarySort(characters, mid + 1, hi);
        System.out.println("右边排序结果 ：" + Arrays.toString(characters));
        mergeSortInPlace(characters, lo, mid, hi - 1);
        System.out.println("自顶向下 最后的结果 ：" + Arrays.toString(characters));
        end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("自顶向下的时间 ：(毫秒)" + (end - begin));

        /**
         * 从低向上 的归并排序  
         *          适用于只用链表是的数据
         */
        begin = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Character[] characters1 = new Character[]{'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        lowToHightSort(characters1);
        System.out.println(Arrays.toString(characters1));
        end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("自低向上" +
                "的时间 ：(毫秒)" + (end - begin));
    }
}
