package com.dragon.talon.algorithm.sort;

import java.util.Arrays;

/**
 * 二叉优先队列， 找最小的
 * 最小的时候可以做成 升序
 *
 * @author dragonboy
 */
public class DescHeapSort<T extends Comparable> {
    private T[] arr;

    private int size;

    public DescHeapSort(T[] arr) {
        this.arr = arr;
    }

    public void insert(T value) {
        arr[size++] = value;
        comeUp(size - 1);
    }

    public T delMin() {
        T temp = arr[0];
        arr[0] = arr[--size];
        arr[size] = null;
        sink(0);
        return temp;
    }

    private void comeUp(int index) {
        while (index > 0 && compare(index / 2, index)) {
            exch(index / 2, index);
            index = index / 2;
        }
    }

    private void sink(int index) {
        while (((index + 1) * 2 - 1) < size) {
            int lo = (index + 1) * 2 - 1;
            if (arr[lo + 1] != null && compare(lo, lo + 1)) {
                lo = lo + 1;
            }
            if (compare(lo, index)) {
                break;
            }
            exch(lo, index);
            index = lo;
        }
    }

    private void sinkSort(int index) {
        int start = 0;
        while (((start + 1) * 2 - 1) < index) {
            int lo = (start + 1) * 2 - 1;
            if (arr[lo + 1] != null && compare(lo, lo + 1)) {
                lo = lo + 1;
            }
            if (compare(lo, start)) {
                break;
            }
            exch(lo, start);
            start = lo;
        }
    }

    public void descSort() {
        int index = size - 1;
        while (index > 0) {
            exch(0, index--);
            sinkSort(index);
        }
    }

    public T[] getArr() {
        return arr;
    }

    private boolean compare(int lo, int hi) {
        return arr[lo].compareTo(arr[hi]) > 0;
    }

    private void exch(int lo, int hi) {
        T temp = arr[lo];
        arr[lo] = arr[hi];
        arr[hi] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        DescHeapSort heapSort = new DescHeapSort(arr);
        heapSort.insert(10);
        heapSort.insert(4);
        heapSort.insert(7);
        heapSort.insert(6);
        heapSort.insert(1);
        heapSort.insert(11);
        heapSort.insert(17);
        heapSort.insert(8);
        heapSort.insert(13);
        heapSort.insert(21);
        heapSort.insert(13);
        heapSort.insert(16);
        System.out.println(Arrays.toString(heapSort.getArr()));
        heapSort.descSort();
        System.out.println(Arrays.toString(heapSort.getArr()));
        /*while (heapSort.getArr()[0] != null) {
            System.out.println(heapSort.delMin());
        }*/


    }
}
