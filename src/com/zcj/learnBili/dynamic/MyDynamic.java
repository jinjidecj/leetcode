package com.zcj.learnBili.dynamic;

import java.util.List;

//动态规划
public class MyDynamic {
    public static void main(String[] args) {
//        System.out.println(new MyDynamic().climbStairs(12));
//        System.out.println(new MyDynamic().rob(new int[]{5, 2, 6, 3, 1, 7}));
        System.out.println(new MyDynamic().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    //爬楼梯，一次只能爬一阶或两阶，求n阶楼梯的爬法有多少种
    public int climbStairs(int n){
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2;i<n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }
    //抢房子问题
    public int rob(int[]nums){
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[nums.length-1];
    }

    //最大子段和
    public int maxSubArray(int[]nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxRes = dp[0];
        for(int i=1;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            if(maxRes<dp[i]){
                maxRes = dp[i];
            }
        }
        return maxRes;
    }
    
    //最少零钱
    public int coinChange(int[]coins,int amount){
        int[]dp = new int[amount+1];
        for(int i = 0;i< amount+1;i++){
            dp[i] = -1;
        }
        dp[0] = 0;
        for(int i = 1;i<=amount;i++){
            for(int j = 0;j<coins.length;j++){
                if(i-coins[j]>=0 && dp[i-coins[j]]!=-1){
                    if(dp[i]==-1 || dp[i]>dp[i-coins[j]]+1){
                        dp[i] = dp[i-coins[j]]+1;
                    }
                }
            }
        }
        return dp[amount];
    }

    //三角形
    public int minTotal(int[][] triangle){
        if(triangle.length==0){
            return 0;
        }
        int[][] dp = new int[triangle.length][triangle[triangle.length-1].length];
        for(int i = 0;i<triangle.length;i++){
            for(int j = 0;j<triangle[i].length;j++)
                dp[i][j] = 0;
        }
        for(int i = 0 ;i<dp.length;i++){
            dp[dp.length-1][i] = triangle[dp.length-1][i];
        }
        for(int i = dp.length-2;i>=0;i--){
            for(int j = 0;j<dp[i].length;j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j];
            }
        }
        return dp[0][0];
    }

    //最长子序列
    public int lengthOfLIS(int[]nums){
        if(nums.length==0){
            return 0;
        }
        int[]dp=new int[nums.length];
        dp[0] = 1;
        int lis = 1;
        for(int i = 1;i<dp.length;i++){
            dp[i] = 1;
            for(int j = 0;j<i;j++){
                if(nums[i]<nums[j] && dp[i]<dp[j]+1){
                    dp[i]=dp[j]+1;
                }
            }
            if(lis<dp[i]){
                lis = dp[i];
            }
        }
        return lis;
    }
}
