package com.dragon.talon.algorithm.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 * 特点：平均排序与比较次数为 (N^2)/4   最好情况 N-1比较和0次比较 最坏情况 (N^2)/2
 * 插入排序对于最坏情况（逆序数组）效果不好，但是对于部分有序的数组会非常高效
 * 对于一般这种无序的数组100000 速度不如选择排序 13s左右
 * insert compareAndExchange : 23
 * @author dragonboy
 */
public class InsertSort {
    public static <T extends Comparable> void sort(T[] arr) {
        if (arr.length <= 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        long count = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0&&arr[j].compareTo(arr[j - 1])<0; j--) {
                count++;
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;

            }
        }
        ArrayList<Integer> array = new ArrayList<Integer>();
        System.out.println(" compare and exchange  : "+ count);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 4, 6, 3, 7, 9, 8, 5, 2, 0};
        LocalDateTime start = LocalDateTime.now();
        sort(arr);
        System.out.println(Duration.between(start, LocalDateTime.now()));
        System.out.println(Arrays.toString(arr));
        Random random = new Random(1000000);
        Integer[] list = new Integer[100000];
        for (int i = 0; i < 100000; i++) {
            list[i]= random.nextInt();
        }

        start = LocalDateTime.now();
        sort(list);
        System.out.println("100000数据选择排序 ：" + Duration.between(start, LocalDateTime.now()));

    }
}
