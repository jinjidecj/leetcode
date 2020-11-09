package com.zcj.learnBili.stackqm;

import java.util.Stack;

/**
 * 有getMin获取整个栈的最小值的函数的栈
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min;
    MinStack(){
        stack = new Stack<>();
        min = new Stack<>();
    }
    public void push(int x){
        stack.push(x);
        if(min.isEmpty()){
            min.push(x);
        }else{
            if(min.peek()<x){
                min.push(min.peek());
            }else {
                min.push(x);
            }
        }
    }
    public int pop(){
        min.pop();
        return stack.pop();
    }
    public int peek(){
        return stack.peek();
    }
    public boolean empty(){
        return stack.isEmpty();
    }
    public int search(int x){
        return stack.search(x);
    }
    public int getMin(){
        return min.peek();
    }
    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(1);
        m.push(2);
        System.out.println(m.peek());
        System.out.println("min:" + m.getMin());
        m.push(-2);
        System.out.println("min:" + m.getMin());
        System.out.println(m.pop());
        System.out.println("min:" + m.getMin());
        System.out.println(m.peek());
        System.out.println("min:" + m.getMin());
    }
}
