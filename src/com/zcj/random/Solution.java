package com.zcj.random;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    int[][] dp = null;
    int n = 0,m = 0;
    int[][] g = null;
    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        if(grid[0][0]==1 || grid[n-1][m-1]==1) return -1;
        g = grid;
        dp = new int[n][m];
        dp[0][0] = 1;

        boolean flag = false;
        for(int i = 1;i<n;i++){
            if(flag){
                if(grid[i][0]==0) dp[i][0]=dp[i-1][0]+1;
                else{
                    flag=false;
                    dp[i][0] = Integer.MAX_VALUE;
                }
            }else
                dp[i][0] = Integer.MAX_VALUE;


        }
        flag = true;
        for(int i = 1;i<m;i++){
            if(flag){
                if(grid[0][i]==0) dp[0][i]=dp[0][i-1]+1;
                else{
                    flag=false;
                    dp[0][i] = Integer.MAX_VALUE;
                }
            }else
                dp[0][i] = Integer.MAX_VALUE;
        }


        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(grid[i][j]==0){
                    dp[i][j] = Math.min(Math.min(getDP(i-1,j),getDP(i,j-1)),getDP(i-1,j-1))+1;
                }
            }
        }
        return dp[n-1][m-1];
    }
    public int getDP(int i,int j){
        return g[i][j]!=0 ? Integer.MAX_VALUE : dp[i][j];
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().shortestPathBinaryMatrix(new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 0, 0}}));
//        System.out.println(new Solution().constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}));
//        System.out.println(new Solution().sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
//        System.out.println(new Solution().summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(new Solution().countServers(new int[][]{
                {1,0,0,1,0},{0,0,0,0,0},{0,0,0,1,0}}));
    }

   public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length==0) return null;
        return construct(nums,0,nums.length-1);
    }
    public TreeNode construct(int[] nums,int start,int end){
        if(start>end) return null;
        if(start==end) return new TreeNode(nums[start]);
        int mid = maxIndex(nums,start,end);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = construct(nums,start,mid-1);
        root.right = construct(nums,mid+1,end);

        return root;
    }

    public int maxIndex(int[] nums,int start,int end){
        if(start>=end) return start;
        int max = nums[start];
        int maxin = start;
        for(int i = start+1;i<=end;i++){
            if(nums[i]>max){
                max = nums[i];
                maxin = i;
            }
        }
        return maxin;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int step = 3;
        if(arr.length<3) return getSum(arr,0,arr.length);
        int sum = getSum(arr,0,arr.length-1);
        while(step<=arr.length){
            for(int i=0;i<arr.length-step+1;i++){
                sum+=getSum(arr,i,i+step-1);
            }
            step+=2;
        }
        return sum;
    }
    public int getSum(int[]arr,int start,int end){
        if(start<0 || end>arr.length-1) return 0;
        int sum = 0;
        for(int i=start;i<=end;i++){
            sum+=arr[i];
        }
        return sum;
    }
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new LinkedList<>();
        int start = 0;
        int end=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]-nums[i-1]==1){
                end = i;
            }
            if(i==nums.length-1 || nums[i]-nums[i-1]!=1){
                if(end==start){
                    result.add(""+nums[start]);
                }else{
                    result.add(""+nums[start]+"->"+nums[end]);
                }
                start=end+1;

            }
        }
        return result;
    }

    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] countRow = new int[m];
        int[] countCol = new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    countRow[i]++;
                    countCol[j]++;
                }
            }
        }
        int ans=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1 && (countRow[i]>1|| countCol[j]>1))
                    ans++;
            }
        }
        return ans;
    }
    
}
