package com.zcj.learnBili.stackqm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉堆--最大堆
 */
public class MaxHeap<T extends Comparable<T>> {
    private List<T> mHeap; //队列，实际是动态数组ArrayList的实例
    public MaxHeap(){
        this.mHeap = new ArrayList<T>();
    }
    //堆的大小
    public int size(){
        return mHeap.size();
    }
    //返回堆顶元素
    public T top(){
        return mHeap.get(0);
    }
    //删除并返回堆顶元素
    public T pop(){
        T i = mHeap.get(0);
        remove(mHeap.get(0));
        return i;
    }
    public boolean empty(){
        return mHeap.isEmpty();
    }
    /**
     * 最大堆的向下调整算法
     * 数组实现的堆中，第N个节点的左孩子的索引值是（2N+1），右孩子的索引是(2N+2)
     * @param start 被下调的节点的起始位置，一般为0，表示从第一个开始
     * @param end 截止范围，一般为数组的最后一个元素的索引
     */
    protected void filterDown(int start,int end){
        int c = start; //当前current节点的位置
        int l = 2*c + 1; //左孩子的位置
        T tmp = mHeap.get(c);
        while (l<end){
            /**
             * compareTo( NumberSubClass referenceName )
             * 如果指定的数与参数相等返回0。
             * 如果指定的数小于参数返回 -1。
             * 如果指定的数大于参数返回 1。
             */
            int cmp = mHeap.get(l).compareTo(mHeap.get(l+1));
            //l是左孩子，l+1是右孩子
            if(l<end && cmp<0){
                l++; //左右孩子中选择较大者，即mHeap[l+1]
            }
            cmp = tmp.compareTo(mHeap.get(l));
            if(cmp>0) break; //调整结束
            else{
                mHeap.set(c,mHeap.get(l));
                c = l;
                l = 2*l+1;
            }
        }
        mHeap.set(c,tmp);
    }

    /**
     * 删除最大堆中的data
     * @param data
     * @return 0-成功  -1-失败
     */
    public int remove(T data){
        //如果堆已经空，则返回-1
        if(mHeap.isEmpty()) return -1;
        //获取data在数据中的索引
        int index  = mHeap.indexOf(data);
        if(index==-1){
            return -1;
        }
        int size = mHeap.size();
        mHeap.set(index,mHeap.get(size-1));//用最后元素填补
        mHeap.remove(size-1);//删除最后一个元素
        if(mHeap.size()>1){
            filterDown(index,mHeap.size()-1);
        }
        return 0;
    }

    /**
     * 最大堆的向上调整法，从start开始直到0,调整堆
     *
     * @param start 被上调的节点的起始位置，一般为数组的最后一个元素的索引
     */
    protected void filterUp(int start){
        int c = start;//当前节点的位置
        int p = (c-1)/2; //父节点的位置
        T tmp = mHeap.get(c); //当前节点的值
        while (c>0){
            int cmp = mHeap.get(p).compareTo(tmp);
            if(cmp>0) break;
            else{
                mHeap.set(c,mHeap.get(p));
                c = p;
                p = (p-1)/2;
            }
        }
        mHeap.set(c,tmp);
    }

    /**
     * 将data插入堆中
     * @param data
     */
    public void insert(T data){
        int size = mHeap.size();
        mHeap.add(data); //将数插在表尾
        filterUp(size);//向上调整堆
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<mHeap.size();i++){
            sb.append(mHeap.get(i)+" ");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int i;
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        MaxHeap<Integer> tree=new MaxHeap<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== 最 大 堆: %s", tree);

        i=85;
        tree.insert(i);
        System.out.printf("\n== 添加元素: %d", i);
        System.out.printf("\n== 最 大 堆: %s", tree);

        i=90;
        tree.remove(i);
        System.out.printf("\n== 删除元素: %d", i);
        System.out.printf("\n== 最 大 堆: %s", tree);
        System.out.printf("\n");
    }
}
