package com.zcj.learnBili.stackqm;

import com.zcj.learnBili.stackqm.heap.MaxHeap;
import com.zcj.learnBili.stackqm.heap.MinHeap;

/**
 * 设计一个数据结构，该数据结构动态维护一组数据，且支持如下操作：
 *  1.添加元素，
 *  2.返回数据的中位数，a.数据个数为奇数，中位数为数组排序后中间的数
 *                  b. 数据个数为偶数，中位数为数组排序后中间两个数字的平均值
 *  解决方案：动态维护一个最大堆和一个最小堆，最大堆存储一般数据，最小堆也是，维持最大堆的堆顶比最小堆的堆顶小
 */
public class MedianFinder {
    private MaxHeap<Integer> bigHeap;
    private MinHeap<Integer> smallHeap;
    public MedianFinder(){
        bigHeap = new MaxHeap<>();
        smallHeap = new MinHeap<>();
    }
    void addNum(int num){
        if(bigHeap.empty()){
            bigHeap.insert(num);
            return;
        }
        if(bigHeap.size()==smallHeap.size()){
            if(num<bigHeap.top()){
                bigHeap.insert(num);
            }else {
                smallHeap.insert(num);
            }
        }else if(bigHeap.size()>smallHeap.size()){
            if(num>bigHeap.top()){
                smallHeap.insert(num);
            }else{
                smallHeap.insert(bigHeap.top());
                bigHeap.pop();
                bigHeap.insert(num);
            }
        }else if(bigHeap.size()<smallHeap.size()){
            if(num<smallHeap.top()){
                bigHeap.insert(num);
            }else {
                bigHeap.insert(smallHeap.top());
                smallHeap.pop();
                smallHeap.insert(num);
            }
        }
    }
    double findMedian(){
        if(bigHeap.size()==smallHeap.size()){
            return (bigHeap.top()+smallHeap.top())/2;
        }else if(bigHeap.size()>smallHeap.size()){
            return bigHeap.top();
        }else{
            return smallHeap.top();
        }
    }

    public static void main(String[] args) {
        MedianFinder m=new MedianFinder();
        int test[]={6,10,1,7,99,4,33};
        for(int i=0;i<7;i++){
            m.addNum(test[i]);
            System.out.println(m.findMedian());
        }
        return;
    }
}
