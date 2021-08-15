package com.dragon.talon.algorithm.arbitrary;

/**
 * @ClassName SmallNums
 * @Version 1.0
 * @Author dragon
 * @Date 2021/8/8 7:09 下午
 * @Description
 **/
public class SmallNums {
    public static void main(String[] args) {
        int [] a = new int[]{0,1,0,1,0,1,99};
        final int i = singleNumber(a);
        System.out.println(i);
    }
    public static int singleNumber(int[] nums) {
        int length =  nums.length;
        int max = 0;
        for(int i=0;i< length;i++){
            if(nums[i]>max){
                max = nums[i];
            }
        }
        int size = length+1;
        if(max > length){
            size =max+1;
        }
        int [] showNum = new int[size];
        for(int i =0;i< length;i++){
            int index =  nums[i];
            showNum[index] +=1;
        }
        int index =-1;
        int total = -1;
        for(int i = 0;i<size;i++){
            if(showNum[i]==0){
                continue;
            }
            if(total == -1){
                index = i;
                total = showNum[i];
                continue;
            }
            if(showNum[i] < total){
                index = i;
                total = showNum[i];
            }
        }
        return index;
    }
}
