package com.zcj.jianzhioffer;

import com.zcj.learnBili.linkedlist.Linked;

import javax.imageio.stream.IIOByteBuffer;
import java.util.*;

public class Session4 {
    public static void main(String[] args) {
//        System.out.println(new Session4().verifyPostorder(new int[]{5, 2, -17, -11, 25, 76, 62, 98, 92, 61}));
        System.out.println(Arrays.asList(new Session4().permutation("abc")));
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //剑指 Offer 27. 二叉树的镜像
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null||(root.left==null && root.right==null)) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if(root.left!=null)  mirrorTree(root.left);
        if(root.right!=null)  mirrorTree(root.right);
        return root;
    }

    //剑指 Offer 28. 对称的二叉树
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root,root);
    }
    public boolean isSymmetric(TreeNode r1,TreeNode r2) {
        if(r1==null&&r2==null) return true;
        if(r1==null||r2==null) return false;
        if(r1.val!=r2.val) return false;
        return isSymmetric(r1.left,r2.right) && isSymmetric(r1.right,r2.left);
    }

    //剑指 Offer 29. 顺时针打印矩阵
    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null || matrix.length<=0 || matrix[0].length<=0)
            return new int[0];
        int start = 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] result = new int[columns*rows];
        int top=0,left=0,right = columns-1,bottom = rows-1;
        int index = 0;
        while (top<=bottom && left<=right){
            for(int i = left;i<=right;i++){
                result[index++]=matrix[top][i];
            }
            for(int i = top+1;i<=bottom;i++){
                result[index++]= matrix[i][right];
            }
            if(left<right&&top<bottom){
                for(int i = right-1;i>left;i--){
                    result[index++]= matrix[bottom][i];
                }
                for(int i = bottom;i>top;i--){
                    result[index++]=matrix[i][left];
                }
            }
            ++left;
            --right;
            ++top;
            --bottom;
        }
        return result;
    }

    //剑指 Offer 30. 包含min函数的栈
    class MinStack {
        private Stack<Integer> data = null;
        private Stack<Integer> min = null;

        /** initialize your data structure here. */
        public MinStack() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if(min.isEmpty()){
                min.push(x);
                return;
            }
            if(min.peek()>x){
                min.push(x);
            }else {
                min.push(min.peek());
            }
        }

        public void pop() {
            min.pop();
            data.pop();
        }

        public int top() {
            return data.peek();
        }

        public int min() {
            return min.peek();
        }
    }

    //剑指 Offer 31. 栈的压入、弹出序列
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed==null||popped==null||pushed.length==0||popped.length==0) return true;
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int i = 0;i<pushed.length;i++){
            stack.push(pushed[i]);
            while (!stack.isEmpty()&& index<popped.length &&stack.peek()==popped[index]){
                stack.pop();
                ++index;
            }
        }
        if(stack.isEmpty() && index==popped.length) return true;
        return  false;
    }

    //剑指 Offer 32 - I. 从上到下打印二叉树
    public int[] levelOrder(TreeNode root) {
        if(root==null) return new int[0];
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode popNode = queue.poll();
            result.add(popNode.val);
            if(popNode.left!=null) queue.add(popNode.left);
            if(popNode.right!=null) queue.add(popNode.right);
        }
        int[] re = new int[result.size()];
        for (int i = 0;i<result.size();i++){
            re[i]=result.get(i);
        }
        return re;
    }
    //剑指 Offer 32 - II. 从上到下打印二叉树 II
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null) return new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        int levelNodeCount = 0;
        int leftNode = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> tempLevel = new LinkedList<>();
        while (!queue.isEmpty()){
            TreeNode tempNode = queue.poll();
            tempLevel.add(tempNode.val);
            if(tempNode.left!=null){
                queue.add(tempNode.left);
                levelNodeCount++;
            }
            if(tempNode.right!=null){
                queue.add(tempNode.right);
                levelNodeCount++;
            }
            leftNode--;
            if(leftNode==0){
                leftNode=levelNodeCount;
                result.add(tempLevel);
                tempLevel = new LinkedList<>();
                levelNodeCount=0;
            }
        }
        return result;
    }
    //剑指 Offer 32 - III. 从上到下打印二叉树 III
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if(root==null) return new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        List<Stack<TreeNode>> stacks = new LinkedList<>();
        stacks.add(new Stack<TreeNode>());
        stacks.add(new Stack<TreeNode>());
        int next = 1;
        int current = 0;
        stacks.get(current).add(root);
        List<Integer> tempList = new LinkedList<>();
        while (!stacks.get(0).isEmpty()||!stacks.get(1).isEmpty()){
            TreeNode tempNode = stacks.get(current).pop();
            tempList.add(tempNode.val);
            if(next==0){
                if(tempNode.right!=null){
                    stacks.get(next).add(tempNode.right);
                }
                if(tempNode.left!=null){
                    stacks.get(next).add(tempNode.left);
                }
            }else {
                if(tempNode.left!=null){
                    stacks.get(next).add(tempNode.left);
                }
                if(tempNode.right!=null){
                    stacks.get(next).add(tempNode.right);
                }
            }
            if(stacks.get(current).isEmpty()){
                current = 1-current;
                next =  1-next;
                result.add(tempList);
                tempList = new LinkedList<>();
            }
        }
        return result;

    }

    //剑指 Offer 33. 二叉搜索树的后序遍历序列
    public boolean verifyPostorder(int[] postorder) {
        return  verifyPostorderCore(postorder,0,postorder.length-1);
    }
    public boolean verifyPostorderCore(int[] postorder,int start,int end){
        if(start>=end) return true;
        int mid = start;
        for(int i = start;i<end;i++){
            if(postorder[i]>postorder[end]) {
                mid = i;
                break;
            }
        }
        for(int i = mid;i<end;i++){
            if(postorder[i]<postorder[end]){
                return  false;
            }
        }
        boolean left = true;
        if(mid>0)
            left = verifyPostorderCore(postorder,start,mid-1);
        boolean right = true;
        if(mid<end){
            right = verifyPostorderCore(postorder,mid,end-1);
        }
        return left && right;

    }

    //剑指 Offer 34. 二叉树中和为某一值的路径
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if(root==null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new LinkedList<>();
        stack.add(root);
        int curSum = 0;
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            curSum+=node.val;
            tempResult.add(node.val);
            if(curSum==sum){
                result.add(cloneList(tempResult));
                stack.pop();
                tempResult = removeLast(tempResult);
                curSum-=node.val;
                continue;
            }
            if(curSum>sum){
                stack.pop();
                tempResult = removeLast(tempResult);
                curSum-=node.val;
                continue;
            }
            if(node.left!=null) stack.add(node.left);
            if(node.right!=null) stack.add(node.right);
            if(node.left==null && node.right==null) {
                curSum-=node.val;
                tempResult = removeLast(tempResult);
                stack.pop();
            }
        }
        return result;
    }
    public List<Integer> cloneList(List<Integer> list){
        List<Integer> newList = new ArrayList<>();
        list.forEach(i->{
            newList.add(i);
        });
        return  newList;
    }
    public List<Integer> removeLast(List<Integer> list){
        List<Integer> newList = new ArrayList<>();
        for(int i = 0;i<list.size()-1;i++){
            newList.add(list.get(i));
        }
        return  newList;
    }
    public int sumList(List<Integer> list){
        int sum = 0;
        for (int i:list){
            sum+=i;
        }
        return sum;
    }

    LinkedList<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        rec(root,sum);
        return result;
    }
    public void rec(TreeNode root,int tar){
        if(root==null) return;
        tar-=root.val;
        if(tar==0 && root.left==null && root.right==null){
            result.add(new LinkedList<>(path));
        }
        rec(root.left,tar);
        rec(root.right,tar);
        path.removeLast();
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    //剑指 Offer 35. 复杂链表的复制
    public Node copyRandomList(Node head) {
//        if(head==null) return head;
//        if(head.next==null) return new Node(head.val);
//        head = copyList(head);
//        return deleteList(head);

        if(head==null) return head;
        if(head.next==null) {
            Node newOne = new Node(head.val);
            if(head.random!=null){
                newOne.random = newOne;
            }
            return newOne;
        }
        Node cur = head.next;
        Map<Node,Node> map = new HashMap<>();
        Node newHead = new Node(head.val);
        Node newCur = newHead;
        map.put(head,newHead);
        while (cur!=null){
            newCur.next = new Node(cur.val);
            map.put(cur,newCur.next);
            cur = cur.next;
            newCur = newCur.next;
        }
        cur = head;
        newCur = newHead;
        while (cur!=null){
            if(cur.random!=null){
                newCur.random = map.get(cur.random);
            }
            cur = cur.next;
            newCur = newCur.next;
        }
        return newHead;
    }
    //在链表的每一个节点后面复制
    public Node copyList(Node head){
        Node temp = head;
        while(temp!=null){
            Node newOne = new Node(temp.val);
            newOne.next = temp.next;
            temp.next = newOne;
            temp = newOne.next;
        }
        temp = head;
        while (temp!=null){
            if(temp.random!=null){
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
        return head;
    }
    //分为两个链表
    public Node deleteList(Node head){
        Node pre = head;
        Node newHead = head.next;
        Node preTemp = newHead;

        Node temp = newHead.next;
        Node newTemp = temp.next;
        while (temp!=null){
            pre.next = temp;
            preTemp.next = newTemp;
            pre = temp;
            preTemp = newTemp;

            temp = newTemp.next;
            if(temp!=null)
                newTemp = temp.next;

        }
        return  newHead;
    }

    //剑指 Offer 37. 序列化二叉树
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "$";
        String s = root.val+"";
        s=s+","+serialize(root.left);
        s=s+","+serialize(root.right);
        return s;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split("," );
        return deserializeCore(s);
    }
    int index = 0;
    public TreeNode deserializeCore(String[] s){
        if(index<s.length&&s[index].equals("$")) return null;
        int val = Integer.parseInt(s[index]);
        TreeNode t = new TreeNode(val);
        index+=1;
        t.left = deserializeCore(s);
        index+=1;
        t.right = deserializeCore(s);
        return t;
    }
    //剑指 Offer 38. 字符串的排列
    public String[] permutation(String s) {
        permutationCore(s,0,"");
        String[] result = new String[res.size()];
        for(int i=0;i<res.size();i++){
            result[i]=res.get(i);
        }
        return result;
    }
    LinkedList<String>res = new LinkedList<>();
    public void permutationCore(String s,int i,String resString){
        if(i==s.length()) res.add(""+resString);
        for(int j=i;j<s.length();j++){
            permutationCore(s,i+1,resString+s.charAt(j));
        }
    }
}
