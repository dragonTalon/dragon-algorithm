package com.dragon.talon.algorithm.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * 希尔排序
 * 一种对于插入排序的升级，使用于大型数据的排序
 * 希尔排序对于插入排序来说，其实就是减少了比较的次数，他对于插入排序来说
 * 它将部分的比较抽简，对数据相隔为h的数据先进行排序（本人数学层度不是特别高，
 * 只能从感觉上来分析算法）
 * 虽然对于大型数据除了希尔排序还有，更好的排序，但是希尔排序实现简单啊，在设计之出可以拿出来用（书上说的～0。0～）
 * 对于一般这种无序的数组100000 速度不如选择排序 0.2s左右  
 * 也可以看对于1～10这十个数组进行的比较交换的次数来看出 hill比insert来说更加的好 count : 11
 * @author dragonboy
 * @date 19/3/10
 */
public class HillSort {
    final static int SPLIT = 3;

    public static <T extends Comparable> void sort(T[] arr) {
        int length = arr.length;
        if (length <= 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int h = 1;
        int compareCoumt = 0;
        while (h < (length / SPLIT)) {
            //作为一种增序的排列从最大到最后等于1 间隔不断缩小
            //3 不知道怎么表述为什么是3其实也可以不是3，只要最后结尾是1结尾的h序列就行
            h = SPLIT * h;
        }
        h++;
        //这样避免了重复创建
        T temp = null;
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && arr[j].compareTo(arr[j - h]) < 0; j -= h) {
                    compareCoumt ++;
                    temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                }
            }
            h = h / SPLIT;
        }
        System.out.println(" compare and exchange  : "+compareCoumt);
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
            list[i] = random.nextInt();
        }

        start = LocalDateTime.now();
        sort(list);
        System.out.println("100000数据选择排序 ：" + Duration.between(start, LocalDateTime.now()));
    }

}
