package com.zcj.learnBili.Tree;

import javax.swing.event.ChangeEvent;
import java.util.*;
import java.util.regex.Pattern;

class TreeNode<T>{
    public T data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){}
    public TreeNode(T d){
        this.data = d;
        this.left = null;
        this.right = null;
    }
    public TreeNode(T d,TreeNode l,TreeNode r){
        this.data = d;
        this.left = l;
        this.right = r;
    }
}
public class Tree<T> {
    public TreeNode<T> head;
    public Tree(){
        this.head = null;
    }
    public Tree(TreeNode t){
        this.head = t;
    }
    /**
     * 遍历类型
     * 前序遍历：根节点->左子树->右子树（根->左->右）
     * 中序遍历：左子树->根节点->右子树（左->根->右）
     * 后序遍历：左子树->右子树->根节点（左->右->根）
     */
    enum Traversal {
        First,Mid,Last
    }

    /**
     * 根据数组创建二叉树，先序，中序，后序
     * @param list
     * @param c
     */
    public Tree<T> createByList(List<T> list, Traversal c){
        switch (c){
            case First:
                this.head = createByListFirst((LinkedList<T>) list);
                break;
            case Mid:
                System.out.println("中序无法创建");
                break;
            case Last:
                //后序序列从后往前分别是根、右子树的后序序列、左子树的后序序列，以最后一个元素构造根结点；
                //因为是从后序序列的最后一个元素构造根结点，所以后序序列应该采用反序。
                reverse(list);
                this.head = createByListLast((LinkedList<T>) list);
                break;
        }
        return this;
    }
    private TreeNode createByListFirst(LinkedList<T> list){
        if(list.peek().equals("#")){
            list.pop();
            return null;
        }
        TreeNode<T> newOne = new TreeNode<T>(list.pop());
        newOne.left = createByListFirst(list);
        newOne.right = createByListFirst(list);
        return newOne;
    }
    private TreeNode createByListLast(LinkedList<T> list){
        if(list.peek().equals("#")){
            list.pop();
            return null;
        }
        TreeNode<T> newOne = new TreeNode<T>(list.pop());
        newOne.right = createByListLast(list);
        newOne.left = createByListLast(list);
        return newOne;
    }
    //反转list
    private void reverse(List<?> list) {
        int size = list.size();
        ListIterator<Object> front = (ListIterator<Object>) list.listIterator();
        ListIterator<Object> back = (ListIterator<Object>) list
                .listIterator(size);
        for (int i = 0; i < size / 2; i++) {
            Object frontNext = front.next();
            Object backPrev = back.previous();
            front.set(backPrev);
            back.set(frontNext);
        }
    }
    /**
     * 打印树
     * @param t
     */
    public void printTree(Traversal t){
        switch (t){
            case First:
                this.traversalFirst(this.head);
                break;
            case Mid:
                this.traversalMid(this.head);
                break;
            case Last:
                this.traversalLast(this.head);
                break;
        }
    }
    private void traversalFirst(TreeNode<T> t){
        if(t==null) return;
        System.out.print(t.data+" ");
        traversalFirst(t.left);
        traversalFirst(t.right);
    }
    private void traversalMid(TreeNode<T> t){
        if(t==null) return;
        traversalMid(t.left);
        System.out.print(t.data+" ");
        traversalMid(t.right);
    }
    private void traversalLast(TreeNode<T> t){
        if(t==null) return;
        traversalLast(t.left);
        traversalLast(t.right);
        System.out.print(t.data+" ");
    }

    //找出二叉树路径和为num的路径
    public void pathSum(TreeNode<Integer> t,int sum){
        List<Stack<Integer>> result = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        preOrderPathSum(t,0,sum,path,result);
        for(int i=0;i<result.size();i++){
            System.out.println(Arrays.asList(result.get(i)));
        }
    }
    public void preOrderPathSum(TreeNode<Integer> node,int pathValue,int sum,Stack<Integer> path,List<Stack<Integer>> result){
        if(node==null){
            return;
        }
        pathValue =pathValue+ Integer.parseInt(String.valueOf(node.data));
        path.add(Integer.parseInt(String.valueOf(node.data)));
        if(pathValue==sum && node.left==null && node.right==null){
            result.add((Stack<Integer>) path.clone());
        }
        preOrderPathSum(node.left,pathValue,sum,path,result);
        preOrderPathSum(node.right,pathValue,sum,path,result);
        pathValue -=Integer.parseInt(String.valueOf(node.data));
        path.pop();

    }

    //找出两个节点最近的公共祖先
    public void findPathToNode(TreeNode<T> node,TreeNode<T> search,
                               Stack<TreeNode<T>> path,Stack<TreeNode<T>> res,boolean finish){
        if(node==null || finish){
            return;
        }
        path.push(node);
        if(node==search){
            finish = true;
            res = (Stack<TreeNode<T>>) path.clone();
        }
        findPathToNode(node.left,search,path,res,finish);
        findPathToNode(node.right,search,path,res,finish);
        path.pop();
    }
    public TreeNode<T> lowestCommonAncestor(TreeNode<T> root,TreeNode<T> p,TreeNode<T> q){
        Stack<TreeNode<T>> path = new Stack<>();
        Stack<TreeNode<T>> nodePpath = new Stack<>();
        Stack<TreeNode<T>> nodeQpath = new Stack<>();
        boolean finish = false;
        findPathToNode(root,p,path,nodePpath,finish);
        path.clear();
        finish = false;
        findPathToNode(root,q,path,nodeQpath,finish);
        int pathLen = 0;
        if(nodePpath.size()<nodeQpath.size()){
            pathLen = nodePpath.size();
        }else{
            pathLen = nodeQpath.size();
        }
        TreeNode<T> result = null;
        for(int i = 0;i<pathLen;i++){
            if(nodePpath.get(i)==nodeQpath.get(i)){
                result  = nodePpath.get(i);
                break;
            }
        }
        return result;
    }

    //把二叉树转化成一条链表，使用right指针作为next指针
    public TreeNode<T> changeToChain(TreeNode<T> node,TreeNode<T> last){
        if(node==null) return null;
        if(node.left==null && node.right==null){
            last = node;
            return node;
        }
        TreeNode<T> left = node.left;
        TreeNode<T> right = node.right;
        TreeNode<T> leftLast = null;
        TreeNode<T> rightLast = null;
        if(left!=null){
            leftLast = changeToChain(left,leftLast);
            node.left = null;
            node.right = left;
            last = leftLast;
        }
        if(right!=null){
            rightLast = changeToChain(right,rightLast);
            if(leftLast!=null){
                leftLast.right = right;
            }
            last = rightLast;
        }
        return rightLast;
    }

    //层次遍历
    public void layerTraversal(TreeNode<T> root){
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode<T> tempNode = queue.peek();
            queue.poll();
            System.out.print(tempNode.data+"-");
            if(tempNode.left!=null){
                queue.add(tempNode.left);
            }
            if(tempNode.right!=null){
                queue.add(tempNode.right);
            }
        }
    }
    //二分查找树插入
    public void BSTInsert(TreeNode<Integer> node,TreeNode<Integer> insertNode){
        if(insertNode.data<node.data){
            if(node.left==null){
                node.left = insertNode;
            }else{
                BSTInsert(node.left,insertNode);
            }
        }else{
            if(node.right==null){
                node.right = insertNode;
            }else{
                BSTInsert(node.right,insertNode);
            }
        }
    }

    //二分查找树查找
    public boolean BSTSearch(TreeNode<Integer> node,int search){
        if(node.data == search){
            return true;
        }
        if(search<node.data){
            if(node.left!=null){
                return BSTSearch(node.left,search);
            }else{
                return false;
            }
        }else{
            if(node.right!=null){
                return BSTSearch(node.right,search);
            }else{
                return false; 
            }
        }
    }

    public static void main(String[] args) {
        String s = "abc##d##e#f##";
        String m = "#c#b#d#a#e#f#";
        String ll = "##c##db###fea";
        String[] l = {"5","4","11","7","#","#","2","#","#"
                ,"#","8","13","#","#","4","5","#","#","1"
                ,"#","#"};
        List<String> n = new LinkedList<>();
        for(int i = 0;i<s.length();i++){
            n.add(s.charAt(i)+"");
        }
        Tree tree = new Tree().createByList(n, Traversal.First);
        tree.printTree(Traversal.First);
        System.out.println();
//        tree.pathSum(tree.head,22);

//        TreeNode<String> temp = tree.head;
//        tree.changeToChain(temp,null);
//        while (temp!=null){
//            System.out.print(temp.data+"-");
//            temp = temp.right;
//        }
        tree.layerTraversal(tree.head);

        Tree<Integer> t1 = new Tree<Integer>(new TreeNode<Integer>(8));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(1));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(2));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(3));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(10));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(15));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(9));
        t1.BSTInsert(t1.head,new TreeNode<Integer>(5));
        t1.printTree(Traversal.Mid);
    }

}
