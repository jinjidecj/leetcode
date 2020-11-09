package com.zcj.learnBili.stackqm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列
 *
 * boolean  add(E e) //将指定的元素插入此队列（如果立即可行且不会违反容量限制），在成功时返回 true，如果当前没有可用的空间，则抛出 IllegalStateException。
 * E        element() //获取，但是不移除此队列的头。
 * boolean  offer(E e) //将指定的元素插入此队列（如果立即可行且不会违反容量限制），当使用有容量限制的队列时，此方法通常要优于 add(E)，后者可能无法插入元素，而只是抛出一个异常。
 * E        peek() //获取但不移除此队列的头；如果此队列为空，则返回 null。
 * E        poll() //获取并移除此队列的头，如果此队列为空，则返回 null。
 * E        remove() //获取并移除此队列的头。
 */
public class QueueLearn {
    public void fun(){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        System.out.println(queue.element());
        printQueue(queue);
        queue.offer(2);
        queue.offer(3);
        printQueue(queue);
        queue.poll();
        printQueue(queue);
        queue.remove();
        printQueue(queue);

    }
    public void printQueue(Queue<Integer> queue){
        System.out.print("[ ");
        for(Integer x:queue){
            System.out.print(x+" ");
        }
        System.out.println(']');
    }

    public static void main(String[] args) {
        new QueueLearn().fun();
    }
}
