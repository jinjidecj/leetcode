package com.zcj.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPro {


    public static void main(String[] args) {
        //1343


        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        System.out.println(list1);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 3, 4);
        System.out.println(list2);
//        list2.
        list2.add(2);

//        System.out.println(new ArrayPro().numOfSubarrays2(new int[]{2,2,2,2,5,5,5,8}, 3, 4));
    }
    //1343
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int result = 0;
        for(int i = 0;i<=arr.length-k;i++){
            int temSum = 0;
            for(int j = i;j<i+k;j++){
                temSum+=arr[j];
            }
            if(temSum/k>=threshold){
                result++;
            }
        }
        return result;
    }
    //滑动窗口解法
    public int numOfSubarrays2(int[] arr, int k, int threshold) {
        int result = 0;
        int targetSum = k * threshold;
        int tempSum = 0;
        for(int i=0;i<k;i++){
            tempSum+=arr[i];
        }
        if(tempSum>=targetSum) result++;
        for(int i = 1;i<arr.length-k;i++){
            tempSum-=arr[i];
            tempSum+=arr[k+i];
            if(tempSum>=targetSum) result++;
        }
        return result;
    }

    //66
    public int[] plusOne(int[] digits) {
        for(int i = digits.length -1;i>=0;i--){
            if(digits[i]+1>=10){
                digits[i]=0;
                if(i==0){
                    //方法一
//                    digits = this.copy(digits);
                    //方法二  到了这一步肯定是首位1，后面全是0
                    digits = new int[digits.length+1];
                    digits[0] = 1;
                }
            }else{
                digits[i]=digits[i]+1;
                break;
            }
        }
        return digits;
    }
    public int[] copy(int[] digits){
        int[] res = new int[digits.length+1];
        res[0]=0;
        for(int i=1;i<digits.length+1;i++){
            res[i]=digits[i-1];
        }


        return res;
    }


}
