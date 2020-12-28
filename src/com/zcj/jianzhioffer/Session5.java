package com.zcj.jianzhioffer;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Session5 {
    //剑指 Offer 39. 数组中出现次数超过一半的数字
    public int majorityElement(int[] nums) {
        int count = 1;
        int x = nums[0];
        for(int i=1;i<nums.length;i++){
            if(count==0){
                x=nums[i];
                count=1;
                continue;
            }
            if(x==nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return x;
    }

    //剑指 Offer 41. 数据流中的中位数
    class MedianFinder {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;
        /** initialize your data structure here. */
        public MedianFinder() {
            left = new PriorityQueue<>((l1,l2)->l2-l1);
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            left.add(num);
            right.add(left.poll());
            if(left.size()+1<right.size()){
                left.add(right.poll());
            }
        }

        public double findMedian() {
            if(right.size()>left.size()) return right.peek();
            return (double)(left.peek()+right.peek())/2;
        }
    }
    //剑指 Offer 44. 数字序列中某一位的数字
    public int findNthDigit(int n) {
        int digit = 1;
        int start = 1;
        int count = 9;
        while (n>count){
            n-=count;
            start=start*10;
            digit ++;
            count=digit*start*9;
        }
        long num = start+ (n-1)/digit;
        return Long.toString(num).charAt((n-1)%digit)-'0';
    }
    //剑指 Offer 45. 把数组排成最小的数
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
    //剑指 Offer 46. 把数字翻译成字符串
    public int translateNum(int num) {
        if(num==0) return 1;
        return translateNumCore(num);
    }
    public int translateNumCore(int num) {
        if(num<10) return 1;
        if(num%100>9 && num %100 < 26){
            return translateNumCore(num/100) + translateNumCore(num/10);
        }else{
            return translateNumCore(num/10);
        }
    }

    //剑指 Offer 47. 礼物的最大价值
    public int maxValue(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        for(int j=1;j<n;j++)
            grid[0][j] +=grid[0][j-1];
        for(int i=1;i<m;i++)
            grid[i][0] +=grid[i-1][0];
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                grid[i][j]+=Math.max(grid[i][j-1],grid[i-1][j]);
        return grid[m-1][n-1];
    }
    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null) return null;
        int lA = 0,lB = 0;
        ListNode tempA = headA,tempB = headB;
        while (tempA != null) {
            lA++;
            tempA=tempA.next;
        }
        while (tempB != null) {
            lB++;
            tempB=tempB.next;
        }
        tempA = headA;
        tempB = headB;
        if(lA>lB){
            while (lA!=lB){
                lA--;
                tempA=tempA.next;
            }
        }else{
            while (lA!=lB){
                lB--;
                tempB=tempB.next;
            }
        }
        while (tempA!=tempB){
            tempA=tempA.next;
            tempB=tempB.next;
        }
        return tempA;
    }
}
