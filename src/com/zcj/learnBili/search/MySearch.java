package com.zcj.learnBili.search;

import java.util.List;

public class MySearch {
    //二分查找-递归
    public boolean binarySearch(List<Integer> list,int begin,int end,int target){
        if(begin>end){
            return false;
        }
        int min = (begin+end)/2;
        if(target==list.get(min)){
            return true;
        }else{
            if(target<list.get(min)){
                return binarySearch(list,begin,min-1,target);
            }else{
                return binarySearch(list,min+2,end,target);
            }
        }
    }
    //二分查找-非递归
    public boolean binarySearch1(List<Integer>list,int target){
        int begin = 0;
        int end = list.size();
        while (end>=begin){
            int min = (begin+end)/2;
            if(list.get(min)==target){
                return true;
            }
            if(list.get(min)<target){
                begin = min+1;
            }else{
                end = min-1;
            }
        }
        return  false;
    }
}
