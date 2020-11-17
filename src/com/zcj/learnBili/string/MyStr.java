package com.zcj.learnBili.string;

import java.util.Arrays;

public class MyStr {
    public static void main(String[] args) {
//        System.out.println(new MyStr().findMax("abcbadab"));

        System.out.println(new MyStr().makeSquare(new int[]{1, 1, 2, 4, 3, 2, 3}));
    }


    //寻找一个字符串中最长的无重复子串的长度
    public int findMax(String s){
        int begin = 0;
        int result = 0;
        String word = "";
        int[] charMap = new int[128];
        for(int i = 0;i<s.length();i++){
            charMap[s.charAt(i)]++;
            if(charMap[s.charAt(i)]==1){
                word+=s.charAt(i);
                if(result<word.length()){
                    result = word.length();
                }
            }else{
                while (begin<i && charMap[s.charAt(i)]>1){
                    charMap[s.charAt(begin)]--;
                    begin++;
                }
                word = "";
                for(int j = begin;j<=i;j++){
                    word+=s.charAt(j);
                }
            }
        }
        return result;
    }

    //给定一个数字序列，判断能否组成一个正方形
    public boolean makeSquare(int[] nums){
        if(nums.length<4) return false;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum+=nums[i];
        }
        if(sum%4!=0) return false;
        Arrays.sort(nums,0,nums.length);
        int[] bucket = new int[4];
        return generateSquare(0,nums,sum/4,bucket);
    }
    public boolean generateSquare(int i,int[] nums,int target,int[]bucket){
        if(i>=nums.length){
            return bucket[0]==target &&bucket[1]==target &&
                    bucket[2]==target &&bucket[3]==target ;
        }
        for(int j = 0;j<4;j++){
            if(nums[i]+bucket[j]>target){
                continue;
            }
            bucket[j]+=nums[i];
            if(generateSquare(i+1,nums,target,bucket)){
                return true;
            }
            bucket[j]-=nums[i];
        }
        return false;
    }

}
