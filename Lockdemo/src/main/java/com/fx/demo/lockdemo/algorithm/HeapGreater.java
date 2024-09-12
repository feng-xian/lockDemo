package com.fx.demo.lockdemo.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 手写堆
 * 加强堆
 */
public class HeapGreater<T> {

    private final ArrayList<T> heap;
    private final HashMap<T, Integer> indexMap;
    private Integer heapSize;

    private final Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }


    public void push(T t) {
        heap.add(t);
        indexMap.put(t, heapSize);
        heapInsert(heapSize);
        heapSize++;
    }

    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    public T peek() {
        return heap.get(0);
    }

    public T pop() {
        T t = heap.get(0);

        indexMap.remove(t);
        swap(0, (heapSize - 1));
        heap.remove(heapSize - 1);
        heapSize--;
        heapIfy(0);

        return t;
    }

    public void remove(T t) {
        Integer index = indexMap.get(t);
        swap(index, (heapSize - 1));
        heapIfy(index);
        indexMap.remove(t);
        heap.remove((heapSize - 1));
        heapSize--;
    }

    public void resign(T t) {
        heapInsert(indexMap.get(t));
        heapIfy(indexMap.get(t));
    }

    private void heapInsert(int index) {

        while (comparator.compare(heap.get(index), heap.get((index + 1) / 2)) < 0) {
            swap(index, (index + 1) / 2);
            index = ((index + 1) / 2);
        }

    }

    private void heapIfy(int index) {

        int left = (index * 2 + 1) / 2;

        while (left < heapSize) {
            int right = ((left + 1) < heapSize) ? left + 1 : left;
            // 越界判断
            int large = (comparator.compare(heap.get(right), heap.get(left))) < 0 ? right : left;

            if (comparator.compare(heap.get(large), heap.get(index)) <= 0) {
                break;
            }

            swap(large, index);
            index = large;

            left = (index * 2 + 1) / 2;
        }

    }


    private void swap(int i1, int i2) {
        T t1 = heap.get(i1);
        T t2 = heap.get(i2);

        heap.set(i1, t2);
        heap.set(i2, t1);

        indexMap.put(t2, i1);
        indexMap.put(t1, i2);

    }









}
