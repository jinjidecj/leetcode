package com.zcj.learnBili.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyRecursion {

    public static void main(String[] args) {
//        List<String> result = new ArrayList<>();
////        new MyRecursion().generateKuohao("",2,result);
//        new MyRecursion().generateOkKuohao("",2,2,result);
//        System.out.println(Arrays.toString(result.toArray()));
        new MyRecursion().solveNQueens(8);
    }

    /**
     * 生成括号,不计算其是否合法
     * @param item 生成的括号字符串
     * @param n 组数
     * @param result 最终结果
     */
    public void generateKuohao(String item, int n, List<String> result){
        if(item.length()==2*n){
            result.add(item);
            return;
        }
        generateKuohao(item+"(",n,result);
        generateKuohao(item+")",n,result);
    }

    /**
     * 生成括号，左括号和右括号都有left=right个
     * @param item
     * @param left
     * @param right
     * @param result
     */
    public void generateOkKuohao(String item,int left,int right, List<String> result){
        if(left==0&&right==0){
            result.add(item);
            return;
        }
        if(left>0){
            generateOkKuohao(item+"(",left-1,right,result);
        }
        if(right>left){
            generateOkKuohao(item+")",left,right-1,result);
        }
    }

    /**
     * n皇后问题
     */
    public static int dx[] = {-1,1,0,0,-1,-1,1,1};
    public static int dy[] = {0,0,-1,1,-1,1,-1,1};
    public void putQueen(int x,int y,int[][] mark){
        mark[x][y]=1;
        for(int i=1;i<mark.length;i++ ){
            for(int j = 0;j<mark.length;j++){
                int new_x = x + i*dx[j];
                int new_y = y + i*dy[j];
                if(new_x>=0&&new_x<mark.length && new_y>=0&&new_y<mark.length){
                    mark[new_x][new_y]=1;
                }
            }
        }
    }
    public void solveNQueens(int n){
        List<List<String>> result = new LinkedList<>();
        int[][] mark = new int[n][n];
        List<String> location = new ArrayList<>();
        for(int i = 0;i<n;i++){
            String re = "";
            for(int j = 0;j<n;j++){
                re+=".";
            }
            location.add(re);
        }
        generateNQueens(0,n,location,result,mark);
        for(int j = 0;j<result.size();j++){
            for(int i = 0;i<result.get(j).size();i++){
                System.out.println(result.get(j).get(i));
            }
            System.out.println();
        }
    }
    public void generateNQueens(int k,int n,List<String> location,List<List<String>> result,int[][] mark){
        if(k==n){
            result.add(listStringClone(location));
            return;
        }
        int[][] tempMark = mark.clone();
        for(int i = 0;i<n;i++){
            if(mark[k][i]==0){
                cloneMark(tempMark,mark);
                String temp = location.get(k);
                location.set(k,subString(location.get(k),i,'Q'));
                putQueen(k,i,mark);
                generateNQueens(k+1,n,location,result,mark);
                cloneMark(mark,tempMark);
//                location.set(k,temp.substring(0,i-1)+'.'+temp.substring(i+1));
                location.set(k,subString(location.get(k),i,'.'));
            }
        }
    }
    public String subString(String s,int i,char a){
        if(i==0){
            return a+s.substring(1);
        }
        if(i==s.length()-1){
            return s.substring(0,i)+a;
        }
        String ss = s.substring(0,i-1);
        String sss = s.substring(i);

        String st = s.substring(0,i-1)+a+s.substring(i);
        return s.substring(0,i)+a+s.substring(i+1);
    }
    //把mark2拷贝给mark1
    public void cloneMark(int[][] mark1,int[][]mark2){
        for(int i = 0;i<mark2.length;i++){
            mark1[i] = mark2[i].clone();
        }
    }
    public List<String> listStringClone(List<String> c){
        List<String> clone = new ArrayList<>();
        for(String s:c){
            clone.add(s);
        }
        return clone;
    }

}
