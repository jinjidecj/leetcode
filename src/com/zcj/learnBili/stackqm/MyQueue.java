package com.zcj.learnBili.stackqm;

import java.util.Stack;

/**
 * 用栈实现队列
 */
public class MyQueue {
    private Stack<Integer> stack = new Stack<>();
    public void push(int x){
        if(stack.isEmpty()){
            stack.push(x);
        }else {
            Stack<Integer> tempStack = new Stack<>();
            while (!stack.isEmpty()){
                tempStack.push(stack.pop());
            }
            tempStack.push(x);
            while (!tempStack.isEmpty()){
                this.stack.push(tempStack.pop());
            }
        }
    }

    //弹出队头元素
    public int pop(){
        return stack.pop();
    }
    //取出队头元素而不删除
    public int peek(){
        return stack.peek();
    }
    public boolean empty(){
        return stack.isEmpty();
    }
    public void print(){
        Utils.printStack(this.stack);
    }

    public void fun(){
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.print();
        System.out.println(queue.pop());
        queue.print();
        queue.push(4);
        System.out.println(queue.peek());
        queue.print();
        System.out.println(queue.empty());
        queue.print();

    }

    public static void main(String[] args) {
        new MyQueue().fun();
    }
}
