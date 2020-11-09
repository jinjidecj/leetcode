package com.zcj.learnBili.stackqm;

import java.util.Queue;
import java.util.Stack;

public class Utils {
    public static void printQueue(Queue<Integer> queue){
        System.out.print("[ ");
        for(Integer x:queue){
            System.out.print(x+" ");
        }
        System.out.println(']');
    }
    public static void printStack(Stack<Integer> stack){
        System.out.print("[ ");
        for(Integer x:stack){
            System.out.print(x+" ");
        }
        System.out.println(']');
    }
}
