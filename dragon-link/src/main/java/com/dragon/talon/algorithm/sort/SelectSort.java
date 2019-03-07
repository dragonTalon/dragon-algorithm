package com.dragon.talon.algorithm.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 选择排序
 * 优点：运行时间与输入无关
 * 移动最少
 * 比较次数：(N^2)/2 次
 * 交换次数：N 次
 *  100000数据数独11.71S
 * @author dragonboy
 */
public class SelectSort {
    public static <T extends Comparable> void selectSortArr(T[] arr) {
        int length = arr.length;
        if (length <= 0) {
            throw new IllegalArgumentException("array not null ");
        }
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[min].compareTo(arr[j]) > 0) {
                    min = j;
                }
            }
            if (i != min) {
                T temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 4, 6, 3, 7, 9, 8, 5, 2, 0};
        LocalDateTime start = LocalDateTime.now();
        selectSortArr(arr);
        System.out.println(Duration.between(start, LocalDateTime.now()));
        System.out.println(Arrays.toString(arr));
        Random random = new Random(1000000);
        Integer[] list = new Integer[100000];
        for (int i = 0; i < 100000; i++) {
            list[i]= random.nextInt();
        }
        
        start = LocalDateTime.now();
        selectSortArr(list);
        System.out.println("100000数据选择排序 ：" + Duration.between(start, LocalDateTime.now()));
      
    }
}
