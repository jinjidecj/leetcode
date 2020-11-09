package com.zcj.learnBili.stackqm;

import java.util.Stack;

/**
 * 栈
 * push( num) //入栈
 * pop() //栈顶元素出栈
 * empty() //判定栈是否为空
 * peek() //获取栈顶元素,不移除
 * search(num) //判端元素num是否在栈中，如果在返回1，不在返回-1
 */
public class StackLearn {
    public void fun(){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        printStack(stack);
        System.out.println(stack.pop());
        if(!stack.isEmpty()){
            printStack(stack);
        }
        System.out.println(stack.peek());
        if(stack.search(2)==1){
            System.out.println("2 is in stack");
        }

    }
    public void printStack(Stack<Integer> stack){
        System.out.print("[ ");
        for(Integer x:stack){
            System.out.print(x+" ");
        }
        System.out.println(']');
    }

    public static void main(String[] args) {
        new StackLearn().fun();
    }
}
