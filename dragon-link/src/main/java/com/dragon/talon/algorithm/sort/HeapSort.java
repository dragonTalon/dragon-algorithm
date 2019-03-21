package com.dragon.talon.algorithm.sort;

import java.util.Arrays;

/**
 * 最大 优先队列
 * 二叉堆
 *  找最大的时候 可以降序
 * @author dragonboy
 */
public class HeapSort<Key extends Comparable<Key>> {
    private Key[] pq;

    private int N = 0;

    public HeapSort(Key[] pq) {
        this.pq = pq;
    }

    public Boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 插入时从1开始计算
     *
     * @param key
     */
    public void insert(Key key) {
        pq[++N] = key;
        //上浮
        comeUp(N);
    }

    public Key delMax() {
        Key key = pq[1];
        exch(1, N);
        pq[N--] = null;
        //下沉
        sink(1);
        return key;
    }

    public Key[] getPq() {
        return pq;
    }

    private void comeUp(int k) {
        while (k > 1 && compare(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int key) {
        while (key * 2 <= N) {
            int i = key * 2;
            if (compare(key * 2, key * 2 + 1)) {
                i = key * 2 + 1;
            }
            if (compare(i, key)) {
                break;
            }
            exch(key, i);
            key = i;
        }
    }

    private boolean compare(int lo, int hi) {
        return pq[lo].compareTo(pq[hi]) < 0;
    }

    private void exch(int lo, int hi) {
        Key temp = pq[lo];
        pq[lo] = pq[hi];
        pq[hi] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        HeapSort heapSort = new HeapSort(arr);
        heapSort.insert(1);
        heapSort.insert(4);
        heapSort.insert(6);
        heapSort.insert(7);
        heapSort.insert(10);
        heapSort.insert(23);
        System.out.println(Arrays.toString(heapSort.getPq()));
        heapSort.delMax();
        System.out.println(Arrays.toString(heapSort.getPq()));
    }
}
