package com.zcj.learnBili.stackqm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列来实现栈
 */
public class MyStack {
    private Queue<Integer> queue;
    MyStack(){
        this.queue = new LinkedList<Integer>();
    }
    //插入元素
    public void push(int x){
        if(this.empty()){
            this.queue.add(x);
        }else{
            Queue<Integer> tempQueue = new LinkedList<Integer>();
            tempQueue.add(x);
            while (!this.queue.isEmpty()){
                tempQueue.add(this.queue.poll());
            }
            while (!tempQueue.isEmpty()){
                this.queue.add(tempQueue.poll());
            }
        }
    }
    //弹出栈顶元素并返回
    public int pop(){
        return this.queue.poll();
    }
    //获取栈顶元素
    public int top(){
        return this.queue.peek();
    }
    //判断是否为空
    boolean empty(){
        return this.queue.isEmpty();
    }
    public void print(){
        Utils.printQueue(this.queue);
    }
    //////////////////////
    public void fun(){
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
        System.out.println(stack.pop());
        stack.print();
        stack.push(4);
        System.out.println(stack.top());
        stack.print();
        System.out.println(stack.empty());
        stack.print();

    }

    public static void main(String[] args) {
        new MyStack().fun();
    }
}
