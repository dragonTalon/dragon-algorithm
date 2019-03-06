package com.dragon.talon.algorithm.search;

/**
 * 二分法查找
 * 
 * @author dragonboy 
 */
public class BinarySearch<T extends Comparable> {
    public static <T extends Comparable> T queryBinarySearch(T[] arr,T targer){
        if (arr.length <=0){
            throw new IllegalArgumentException("array length less than 0");
        }
        int hi = arr.length;
        int lo = 0;
        //= 是因为有数组为偶数的情况
        while (lo <= hi){
            int index = lo + (hi-lo)/2;
            if (targer.compareTo(arr[index]) > 0){
                lo = index+1;
            }else if (targer.compareTo(arr[index]) < 0){
                hi = index-1;
            }else {
                return arr[index];
            }
        }
        return null;
    }

    public static void main(String[] args) {
      Integer [] arry = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(queryBinarySearch(arry,8));
    }
}
