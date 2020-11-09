package com.zcj.learnBili.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Linked<T extends  Comparable> {
    private class Node{
        private T t;
        private Node next;
        public Node(T t,Node n){
            this.t = t;
            this.next = n;
        }
        public Node(T t){
            this(t,null);
        }
    }
    private Node head;
    private int size;
    public Linked(){
        this.head = null;
        this.size = 0;
    }
    //向链表头添加节点
    public void addFirst(T t){
        Node n = new Node(t);
        if(this.head!=null){
            n.next = this.head;
        }
        this.head = n;
        this.size++;
    }
    //向链表尾添加节点
    public void addLast(T t){
        this.add(t,this.size);
    }
    //向链表中间添加元素
    public void add(T t,int index){
        if(index<0||index>this.size){
            throw new IllegalArgumentException("index error");
        }
        if(index==0){
            this.addFirst(t);
        }
        Node before = this.head;
        for(int i=0;i<index-1;i++){
            before = before.next;
        }
        Node newOne = new Node(t,before.next);
        before.next = newOne;
        this.size ++;
    }
    //删除链表中所有值为t的节点
    public boolean delete(T t){
        if(this.head==null){
            return false;
        }
        while (this.head!=null && this.head.t.equals(t)){
            this.head = this.head.next;
            this.size--;
        }
        Node cur = this.head;
        while (cur!=null&&cur.next!=null){
            if(cur.next.t.equals(t)){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return true;
    }
    //删除第几个节点
    public boolean delete(int index){
        if(index<=0||this.head==null||index>this.size){
            return false;
        }
        Node befo = this.head;
        for(int i = 1;i<index;i++){
            befo = befo.next;
        }
        befo.next = befo.next.next;
        this.size--;
        return true;
    }
    //删除第一个节点
    public Node deleteFirst(){
        if(this.head==null){
            System.out.println("linked is empty");
            return null;
        }
        Node returnNode = this.head;
        this.head = this.head.next;
        this.size--;
        return returnNode;
    }
    //删除最后一个节点
    public Node deleteLast(){
        if(this.head==null){
            System.out.println("linked is empty");
            return null;
        }
        if(this.size==1){
            return this.deleteFirst();
        }
        Node cur = this.head;
        Node next = this.head.next;
        while (next.next!=null){
            cur = next;
            next = next.next;
        }
        cur.next = null;
        this.size--;
        return next;
    }
    //判断是否包含值t
    public boolean contains(T t){
        Node cur = this.head;
        while (cur!=null){
            if(cur.t.equals(t)){
                return true;
            }else{
                cur = cur.next;
            }
        }
        return false;
    }
    //获取最后一个节点
    public Node getLastNode(){
        Node find = this.head;
        while (find.next!=null)
            find = find.next;
        return find;
    }

    //逆序当前链表
    public Node reverseLinked(){
        if(this.head==null){
            System.out.println("linked is empty");
            return null;
        }
        Node newHead = null;
        while (head!=null){
            Node temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        head = newHead;
        return newHead;
    }
    //从第m个节点开始逆序，到第n个节点
    public Node reverseBetween(int m,int n){
        if(n>this.size){
            System.out.println("index n out of index");
        }
        int change = n - m + 1;
        Node pre = null;//开始逆序的节点的前驱
        Node cur = this.head;
        while (cur!=null && --m!=0){
            pre = cur;
            cur = cur.next;
        }
        Node modifyListTail = cur;
        Node newHead = null;
        while (cur!=null && change!=0){
            Node next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
            change--;
        }
        modifyListTail.next = cur;
        if(pre!=null){
            pre.next =newHead;
        }else {
            pre = newHead;
        }
        return this.head;
    }

    //查找两个链表是否有交点=======================================================
    //法一，通过放入set集合，查看是否有相同的节点已经被放入了
    public Node getIntersectionNode(Node a,Node b){
        Set<Node> nodeSet = new HashSet<>();
        while (a!=null){
            nodeSet.add(a);
            a = a.next;
        }
        while (b!=null){
            if(nodeSet.contains(b)){
                return b;
            }
            b = b.next;
        }
        return null;
    }
    //法二，计算两个链表的长度，然后截去最长的链表，aHead和bHead同时往后遍历，如果aHead==bHead，则说明有交点
    public Node getIntersectionNode2(Linked<Integer> a,Linked<Integer> b){
        Linked.Node ahead = a.head;
        Linked.Node bhead = b.head;
        if(a.size>b.size){
            ahead = a.moveLinked(a.head,a.size - b.size + 1);
        }else {
            bhead = b.moveLinked(b.head,b.size - a.size + 1);
        }
        while (ahead!=null){
            if(ahead==bhead){
                return ahead;
            }else {
                ahead = ahead.next;
                bhead = bhead.next;
            }
        }
        return null;
    }
    //移动i个节点，返回第i个节点
    public Node moveLinked(Node a,int i){
        while (i!=1&&a!=null){
            a = a.next;
            i--;
        }
        return a;
    }
    //=========================================================================

    //判断链表里是否存在环========================================================
    //法一，将链表节点放入set集合，如果有相同点放入了，则存在环，第一次相同的则是环的起点
    //法二，快慢指针
    public Node detectCycle(){
        if(this.size == 0){
            return null;
        }
        Node fast = this.head;
        Node slow = this.head;
        Node meet = null;
        while (fast!=null){
            fast = fast.next;
            slow = slow.next;
            if(fast!=null){
                fast = fast.next;//fast不是末尾，
            }else{
                return null;//fast到了末尾
            }
            if(fast == slow){
                meet = fast;//fast和slow相遇，记录相遇的位置
                break;
            }
        }
        if(meet==null){
            return null;
        }
        Node h = this.head;
        while (h!=null&&meet!=null){
            if(h==meet){
                return  h;
            }
            h = h.next;
            meet = meet.next;
        }
        return null;
    }
    //=========================================================================

    //将链表中大于x的放右边，小于x的放左边，不改变其在原来链表的次序=====================
    //解决方法：用两个临时链表，一个放小于x的，一个放大于x的，遍历原链表，完成后合并两个链表
    public Linked partition(int x){
        Linked<T> linkedLess = new Linked();
        Linked<T> linkedLarge = new Linked();
        Node cur = this.head;
        //T首先extends Comparable, 然后用compareTo来比较 a > b 是a.compareTo(b) > 0, a = b 是a.compareTo(b) == 0, a < b 是a.compareTo(b) < 0.
        while (cur!=null){
            if( cur.t.compareTo(x) < 0){
                linkedLess.addLast(cur.t);
            }else{
                linkedLarge.addLast(cur.t);
            }
            cur = cur.next;
        }
        linkedLess.getLastNode().next = linkedLarge.head;
        linkedLarge.getLastNode().next = null;
        return linkedLess;
    }
    //=========================================================================
    //合并两个已经排序的链表
    public Linked mergeTwoLinked(Linked<T> a,Linked<T> b){
        Linked<T> temp = new Linked<>();
        Node pre = temp.head;
        temp.size = a.size+b.size;
        Node ahead = a.head;
        Node bhead = b.head;
        while (a.size!=0 && b.size!=0){
            if(ahead.t.compareTo(bhead.t)<0){
                if(pre==null){
                    pre = ahead;
                }else{
                    pre.next = ahead;
                }
                ahead = ahead.next;
            }else{
                if(pre==null){
                    pre = bhead;
                }else{
                    pre.next = bhead;
                }
                bhead = bhead.next;
            }
        }
        if(a.size!=0){
            pre.next = ahead;
        }
        if(b.size!=0){
            pre.next = bhead;
        }
        return temp;
    }
    /**
     * 合并k个已经排序的链表，合并后仍为有序的
     * 方案一：k个链表按顺序合并k-1次，即第一个和第二个合并，然后和第三个合并，
     *      设有k个链表，平均每个链表有n个节点，时间复杂度：
     *      (n+n)+(2n+n)+...+((k-1)n+n) = O(k^2*n)
     * 方案二：将k*n个节点放到vector中，再将vector排序，再将节点顺序相连
     *      时间复杂度kN*logkN + KN = O(kN*logkN)
     * 方案三：分治后相连
     *      第一轮，进行k/2次，每次处理2n个数字，第二轮，进行k/4次，每次处理4n个数字。。。
     *      最后一次，进行k/(2^logk)次，每次处理2^logk*N个值
     *      2N*k/2 + 4N*k/4 + 8N*k/8 .. + 2^logk*N * K/(2^logk) = NK+NK+...+Nk = O(kNlogk)
     *
     *
     */


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node cur = this.head;
        while (cur!=null){
            sb.append(cur.t+"->");
            cur = cur.next;
        }
        sb.append("null");
        return  sb.toString();
    }
    public static void main(String[] args) {
        Linked<Integer> linked = new Linked();
        for(int i = 0; i < 10; i++){
            linked.addFirst(i);
        }
        System.out.println(linked);
        linked.addLast(33);
        linked.addFirst(33);
        linked.add(33, 5);
        System.out.println(linked);
//        linked.delete(33);
//        System.out.println(linked);
//        System.out.println("删除第一个元素："+linked.deleteFirst().t);
//        System.out.println(linked);
//        System.out.println("删除最后一个元素："+linked.deleteLast().t);
//        System.out.println(linked);
//        linked.reverseLinked();
//        System.out.println(linked);
//        linked.reverseBetween(2,6);
//        System.out.println(linked);
//        System.out.println(linked.partition(7));
//        System.out.println(linked.moveLinked(linked.head, 2).t);
    }
}
