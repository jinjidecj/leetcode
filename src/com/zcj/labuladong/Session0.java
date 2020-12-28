package com.zcj.labuladong;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Session0 {
    public static void main(String[] args) {
        printTime();
        System.out.println(new Session0().coinChange(new int[]{1, 2, 5}, 11));
        printTime();
        System.out.println(new Session0().coinChangeWithMemory(new int[]{1, 2, 5}, 11));
        printTime();
    }
    public static void printTime(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
    }
    public int coinChange(int[] coins,int amount){
        if(amount==0) return 0;
        if(amount<0) return -1;
        int result = Integer.MAX_VALUE;
        for(int i = 0;i<coins.length;i++){
            int sub = coinChange(coins,amount-coins[i]);
            if(sub==-1) continue;
            result = Math.min(result,1+sub);
        }
        return result==Integer.MAX_VALUE? -1:result;
    }
    int[]memo=null;
    public int coinChangeWithMemory(int[] coins,int amount){
        memo = new int[amount+1];
        return coinChange1(coins,amount);
    }
    public int coinChange1(int[] coins,int amount){
        if(memo[amount]!=0) return memo[amount];
        if(amount==0) return 0;
        if(amount<0) return -1;
        int result = Integer.MAX_VALUE;
        for(int i = 0;i<coins.length;i++){
            int sub = coinChange(coins,amount-coins[i]);
            if(sub==-1) continue;
            result = Math.min(result,1+sub);
        }
        memo[amount]=result==Integer.MAX_VALUE? -1:result;
        return memo[amount] ;
    }

    //n皇后
//    List<List<String>> result = new LinkedList<>();
//    List<String> one = new LinkedList<>();
//    List<List<String>> chart = new ArrayList<>();
//    int n;
//    public List<List<String>> solveNQueens(int n) {
//        initChart(n);
//        solveNQueensCore(0);
//        this.n = n;
//        return result;
//    }
//    public void solveNQueensCore(int row){
//        if(row==this.n){
//            addResult();
//        }
//        for(int i=0;i<n;i++){
//
//        }
//    }
//    public boolean isAvailable(int row,int colum){
//        for(int i=0;i<n;i++){
//            if(chart.get(i).get(colum).equals("Q")){
//                return false;
//            }
//        }
//
//    }
//    public void initChart(int n){
//        List<String> s = new ArrayList<>();
//        for(int i=0;i<n;i++){
//            s.add(".");
//        }
//        for(int i=0;i<n;i++){
//            chart.add(new ArrayList<>(s));
//        }
//    }
//    public void addResult(){
//        String str = "";
//        for(List<String> s:chart){
//
//        }
//    }

    //72. 编辑距离
    int[] w1,w2;
    int[][] result;
    String word1,word2;
    public int minDistance(String word1, String word2) {
        w1=new int[word1.length()];
        w2=new int[word2.length()];
        result = new int[word1.length()][word2.length()];
        this.word1 = word1;
        this.word2 = word2;
        return core(word1.length()-1,word2.length()-1);
    }
    public int core(int i,int j){
        if(result[i][j]!=0) return result[i][j];
        if(i<0) return j+1;
        if(j<0) return i+1;
        if(word1.charAt(i)==word2.charAt(j)){
            return core(i-1,j-1);
        }else{
            result[i][j] = Math.min(Math.min(core(i-1,j)+1  //删除
                    ,core(i,j-1)+1)  //插入
                    ,core(i-1,j-1)+1); //替换
            return result[i][j];
        }

    }

}
