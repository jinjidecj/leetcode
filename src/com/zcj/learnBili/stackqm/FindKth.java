package com.zcj.learnBili.stackqm;

import com.zcj.learnBili.stackqm.heap.MinHeap;

/**
 * 已知一个未排序的数组，求这个数组第k大的数字
 * 解法：维护一个k大小的最小堆，堆中元素小于k时，新元素直接加入，否则当堆顶元素小于新元素时，弹出堆顶，将新元素加入堆。
 * 由于堆是最小堆，堆顶是堆中最小堆，新元素保证比堆顶小，第k大个。
 */
public class FindKth {
    public static void main(String[] args) {
        int i;
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        System.out.println(findKthLargest(a, 2));
    }
    public static int findKthLargest(int [] a,int k){
        MinHeap<Integer> tree=new MinHeap<Integer>();
        for(int i = 0;i<a.length;i++){
            if(tree.size()<k){
                tree.insert(a[i]);
            }else if(tree.top()<a[i]){
                tree.pop();
                tree.insert(a[i]);
            }
//            System.out.println(tree.toString());
        }
        return tree.top();
    }
}
