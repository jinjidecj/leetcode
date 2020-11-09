//package com.zcj.array;
//public class 2{
//
//}
//class Solution {
//    public static void main(String[] args) {
//        new Solution1().numOfSubarrays([2,2,2,2,5,5,5,8],
//        3,
//        4);
//    }
//    public int numOfSubarrays(int[] arr, int k, int threshold) {
//        int result = 0;
//        for(int i = 0;i<arr.length-k;i++){
//            int temSum = 0;
//            for(int j = i;j<i+k;j++){
//                temSum+=arr[j];
//            }
//            if(temSum/k>=threshold){
//                result++;
//            }
//        }
//        return result;
//    }
//    public int avg(int [] arr, int start,int k){
//        int sum = 0;
//        for(int i = start;i<arr.length&&i<k+start;i++){
//            sum+=arr[i];
//        }
//        return sum/k;
//    }
//}