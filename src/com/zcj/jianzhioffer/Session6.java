package com.zcj.jianzhioffer;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;

public class Session6 {
    //剑指 Offer 54. 二叉搜索树的第k大节点
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //剑指 Offer 55 - I. 二叉树的深度
    int res,k;
    public int kthLargest(TreeNode root, int k) {
        this.k=k;
        kthLargestCore(root);
        return res;
    }
    public void kthLargestCore(TreeNode root){
        if(root==null) return ;
        kthLargestCore(root.right);
        if(k==0) return;
        if(--k==0) res = root.val;
        kthLargestCore(root.left);
    }

    //剑指 Offer 55 - II. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }
    private int recur(TreeNode root){
        if(root==null) return 0;
        int left = recur(root.left);
        if(left==-1) return -1;
        int right = recur(root.right);
        if(right==-1) return -1;
        return Math.abs(left-right)<2 ? Math.max(left,right)+1:-1;
    }
    //剑指 Offer 56 - I. 数组中数字出现的次数
    public int[] singleNumbers(int[] nums) {
        int temp =0;
        for(int i:nums){
            temp = temp ^ i;
        }
        int index = findFirstBit(temp);
        int num1 = 0,num2 = 0;
        for(int i:nums){
            if(isBit1(i,index)){
                num1 = num1 ^i;
            }else{
                num2 = num2 ^ i;
            }
        }
        int[] result = new int[2];
        result[0]=num1;
        result[1]=num2;
        return result;
    }
    public int findFirstBit(int num){
        int index = 0;
        while ((num & 1)==0 && index<8*Integer.SIZE ){
            num = num>>1;
            index++;
        }
        return index;
    }
    public boolean isBit1(int num,int index){
        num = num>>index;
        return (num&1)!=0;
    }

    //剑指 Offer 57. 和为s的两个数字
    public int[] twoSum(int[] nums, int target) {
        int i=0,j=nums.length-1;
        while (i<j){
            int temp = nums[i]+nums[j];
            if(temp==target) break;
            else if(temp<target) i++;
            else j--;
        }
        if(i<j) return new int[]{nums[i],nums[j]};
        else return new int[]{};
    }

    //剑指 Offer 57 - II. 和为s的连续正数序列
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        int i=0,j=1;
        while (i<j){
            int sum = (i+j)*(j-i+1)/2;
            if(sum==target){
                int[]temp = new int[j-i+1];
                for(int k=i;k<=j;k++){
                    temp[k-i]=k;
                }
                res.add(temp);
                j++;
            }else if(sum>target){
                i++;
            }else {
                j++;
            }

        }
        return res.toArray(new int[res.size()][]);
    }

    //剑指 Offer 58 - I. 翻转单词顺序
    public String reverseWords(String s) {
        String newS = s.trim();
        newS = reverse(newS);
        String[] temp = newS.split(" ");
        String result = "";
        for(String i :temp){
            if(!i.equals(""))
                result+=reverse(i.trim())+" ";
        }
        return result.trim();
    }
    private String reverse(String s){
        return new StringBuffer(s).reverse().toString();
    }
    //剑指 Offer 58 - II. 左旋转字符串
    public String reverseLeftWords(String s, int n) {
        return s.substring(n)+s.substring(0,n);
    }

    //剑指 Offer 59 - II. 队列的最大值
    static class MaxQueue {
        Deque<Integer> max;
        Queue<Integer> data;
        public MaxQueue() {
            max = new LinkedList<>();
            data = new LinkedList<>();
        }

        public int max_value() {
            if(max.isEmpty()) return -1;
            return max.peekFirst();
        }

        public void push_back(int value) {
            while (!max.isEmpty()&& max.peekLast()<value){
                max.pollLast();
            }
            data.offer(value);
            max.offerLast(value);
        }

        public int pop_front() {
            if(data.isEmpty()) return -1;
//            int ans = data.poll();
//            if(ans==max.peekFirst()) max.pollFirst();
//            return ans;
            if(data.peek().equals(max.peekFirst())) max.pollFirst(); //大于127的Integer对象要用equals()比较，不能用==
            return data.poll();
        }
    }
    //剑指 Offer 61. 扑克牌中的顺子
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero=0;
        for(int i:nums){
            if(i==0) zero++;
        }
        int maxCrap = 0;
        int small = 0;
        int big = 1;
        while (big<nums.length){
            if(nums[small]==0||nums[big]==0) {
                small=big;
                big++;
                continue;
            }
            if(nums[small]==nums[big]) return false;
            maxCrap+=nums[big]-nums[small]-1;
            small=big;
            big++;
        }
        if(maxCrap>zero) return false;
        return true;
    }
    //剑指 Offer 63. 股票的最大利润
    public int maxProfit(int[] prices) {
        if(prices.length<2) return 0;
        int min = prices[0];
        int maxP = prices[1]-min;
        for(int i=2;i<prices.length;i++){
            if(prices[i-1]<min) min=prices[i-1];
            int tempP = prices[i]-min;
            if(tempP>maxP) maxP = tempP;
        }
        return maxP;
    }

    //剑指 Offer 67. 把字符串转换成整数
    public int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        if(c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if(c[0] == '-') sign = -1;
        else if(c[0] != '+') i = 0;
        for(int j = i; j < c.length; j++) {
            if(c[j] < '0' || c[j] > '9') break;
            if(res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(p.val<root.val && q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }else if(p.val>root.val && q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }else{
            return root;
        }
    }
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q) return root;
        TreeNode left = lowestCommonAncestor2(root.left,p,q);
        TreeNode right = lowestCommonAncestor2(root.right,p,q);
        if(left!=null&& right!=null) return root;
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }
    public static void main(String[] args) {
//        System.out.println(new Session6().reverseWords("a good   example"));
        MaxQueue m = new MaxQueue();
        System.out.println(m.max_value());
        System.out.println(m.pop_front());
        System.out.println(m.max_value());
        m.push_back(46);
        System.out.println(m.max_value());
        System.out.println(m.pop_front());
        System.out.println(m.max_value());
        System.out.println(m.pop_front());
        m.push_back(868);
        System.out.println(m.pop_front());
        System.out.println(m.pop_front());
        System.out.println(m.pop_front());
        m.push_back(525);
        System.out.println(m.pop_front());
        System.out.println(m.max_value());
        System.out.println(m.pop_front());
        System.out.println(m.max_value());


    }
}
