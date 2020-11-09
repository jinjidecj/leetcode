package com.zcj.learnBili.stackqm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 合法的出栈序列
 * 已知1~n的数字序列，按顺序入栈，每个数字入栈后即可出栈，也可在栈中停留，等待后面的数字入栈出栈后，该数字再出栈
 * 求给定的出栈序列是否合法
 * 合法：3 2 5 4 1
 * 不合法： 3 1 2 4 5
 */
public class SequenceStack {
    /**
     * 合法的出栈序列
     * @param order 给定的要判断是否合法的序列
     * @return 是否合法
     */
    public static boolean isValid(Queue<Integer> order){
        Stack<Integer> temp = new Stack<>();
        int n = order.size();//保证循环的次数不变，不可以直接用i<=order.size()来作为for的判断条件，因为order的长度会变
        for(int i = 1;i<=n;i++){
            temp.push(i);
            while (!temp.empty()&&order.peek()==temp.peek()){
                temp.pop();
                order.poll();
            }
        }
        if(temp.isEmpty()){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Queue<Integer> order = new LinkedList<>();
        order.add(3);
        order.add(2);
        order.add(5);
        order.add(4);
        order.add(1);
        System.out.println(isValid(order));
        order.clear();
        order.add(3);
        order.add(1);
        order.add(2);
        order.add(4);
        order.add(5);
        System.out.println(isValid(order));


    }
}
