package com.zcj.jianzhioffer;

import com.zcj.learnBili.Tree.Tree;

import java.util.*;

public class Session2 {
    public static void main(String[] args) {
//        System.out.println(new Session2().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
//        System.out.println(new Session2().replaceSpace("We are happy."));
//        System.out.println(new Session2().exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
//        System.out.println(new Session2().movingCount(2, 3, 1));
//        System.out.println(new Session2().movingCount(3, 1, 0));
//        System.out.println(new Session2().cuttingRope(10));
//        System.out.println(new Session2().cuttingRope1(10));
    }


    //剑指 Offer 03. 数组中重复的数字
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer,Integer> hasNums = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            if(hasNums.get(nums[i])!=null){
                return nums[i];
            }else{
                hasNums.put(nums[i],1);
            }
        }
        return 0;
    }
    //剑指 Offer 04. 二维数组中的查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        int height = matrix.length;
        int width=0;
        if(height==0){
            return false;
        }
        width = matrix[0].length;
        for(int i=0,j=width-1;i<height && j>0;){
            if(matrix[i][j]==target) return true;
            if(matrix[i][j]>target) j--;
            else if(matrix[i][j]<target) i++;
        }
        return false;
    }

    //剑指 Offer 05. 替换空格
    public String replaceSpace(String s) {
        String ss = s.replaceAll(" ","%20");
        System.out.println(s);
        return ss;
    }

    //剑指 Offer 06. 从尾到头打印链表
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public int[] reversePrint(ListNode head) {
        Stack<Integer> s = new Stack<>();
        ListNode h = head;
        int count = 0;
        while(h!=null){
            s.push(h.val);
            count++;
            h = h.next;
        }
        int[] result = new int[count];
        for(int i = 0;i<count;i++) result[i] = s.pop();
        System.out.println(Arrays.asList(s));
        return result;
    }

    //剑指 Offer 07. 重建二叉树
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeSub(preorder,inorder,0,preorder.length,0,inorder.length);
    }
    private TreeNode buildTreeSub(int[] preorder,int[] inorder,int startP,int endP,int startI,int endI){
        if(startP==endP){
            if(startI==endI && preorder[startP]==inorder[startI])
                return new TreeNode(preorder[startP]);
        }
        TreeNode t = new TreeNode(preorder[startP]);

        int newEndP = getIndex(inorder,preorder[startI]);
        int leftLength = newEndP - startI;
        int leftEndP = startP + leftLength;
        if(leftLength>0)
            t.left = buildTreeSub(preorder,inorder,startP+1,leftEndP,startI,endI-1);
        if(leftLength<endP-startP)
            t.right = buildTreeSub(preorder,inorder,leftEndP+1,endP,startI+1,endI);
        return t;
    }
    private int getIndex(int[] array,int a){
        for(int i = 0;i<array.length;i++){
            if(array[i]==a) return i;
        }
        return -1;
    }

    //剑指 Offer 09. 用两个栈实现队列
    class CQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue() {
            stack1 = new LinkedList<Integer>();
            stack2 = new LinkedList<Integer>();
        }


        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if(stack1.isEmpty()&&stack2.isEmpty()){
                return -1;
            }
            if(!stack2.isEmpty()){
                return stack2.pop();
            }else if(!stack1.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
            return -1;
        }
    }

    //剑指 Offer 10- I. 斐波那契数列
    public int fib(int n) {
        int[] res = new int[]{0,1};
        if(n<2){
            return res[n];
        }
        int fibN1 = 1;
        int fibN2 = 0;
        int fibN = 0;
        for(int i = 2;i<=n;i++){
            fibN = fibN1+fibN2;
            fibN2 = fibN1;
            fibN1 = fibN;
        }
        return fibN;
    }

    //剑指 Offer 11. 旋转数组的最小数字
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length-1;
        while (low<high){
            int mid = low + (high-low)/2;
            if(numbers[mid]<numbers[high]){
                high = mid;
            }else if(numbers[mid]>numbers[high]){
                low = mid+1;
            }else{
                high-=1;
            }
        }
        return numbers[low];
    }

    //剑指 Offer 12. 矩阵中的路径
    public boolean exist(char[][] board, String word) {
        if(word.equals("")||board.length==0) return false;
        if(word.length()>board.length*board[0].length) return false;
        int[][] exited = new int[board.length][board[0].length];
//        for(int i = 0;i<board.length;i++){
//            exited[i] = new int[board[i].length];
//        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==word.charAt(0)){
                    if(existCore(board,word,i,j,1,exited)){
                        return true;
                    }else{
                        exited = new int[board.length][board[0].length];
                    }
                }
            }
        }
        return false;
    }
    public boolean existCore(char[][] board, String word,int startI,int startJ,int wordIndex,int[][]exited){
        if(wordIndex==word.length()) return true;
        exited[startI][startJ]=1;
        //偏移坐标，左，上，右，下
        int[] plusX = new int[]{-1,0,1,0};
        int[] plusY = new int[]{0,-1,0,1};
        for(int i = 0;i<4;i++){
            int newX = startI+plusX[i];
            int newY = startJ+plusY[i];
            if(newX>=0&&newX<board.length && newY>=0 && newY<board[0].length && exited[newX][newY]!=1){
                if(board[newX][newY]== word.charAt(wordIndex)){
                    if(existCore(board,word,newX,newY,wordIndex+1,exited)){
                        return true;
                    }
                }
            }
        }
        exited[startI][startJ] = 0;
        return false;
    }

    //剑指 Offer 13. 机器人的运动范围
    public int movingCount(int m, int n, int k) {
        if(k==0) return 1;
        int[][] grid = new int[m][n];
        return movingCount(0,0,k,grid);
    }
    public int movingCount(int m,int n,int k,int[][] grid){
        if(!isAvailableGrid(m,n,k)){
            return 0;
        }
        grid[m][n]=1;
        int[] plusX = new int[]{-1,0,1,0};
        int[] plusY = new int[]{0,-1,0,1};
        int count = 1;
        for(int i = 0; i < 4; i++){
            int newX = m+plusX[i];
            int newY = n+plusY[i];
            if(newX>=0 && newX < grid.length && newY>=0 && newY < grid[0].length && grid[newX][newY]==0){
                count+=movingCount(newX,newY,k,grid);
            }
        }
        return count;
    }
    public boolean isAvailableGrid(int m,int n,int k){
        int sum = 0;
        while (m!=0){
            sum+=m%10;
            m=m/10;
        }
        while (n!=0){
            sum+=n%10;
            n=n/10;
        }
        return sum>k?false:true;
    }

    //剑指 Offer 14- I. 剪绳子  2 <= n <= 58
    public int cuttingRope(int n) {
        int[] rope = new int[n+1];
        if(n==2) return 1;
        if(n==3) return 2;
        rope[0] = 0;
        rope[1] = 1;
        rope[2] = 2;
        rope[3] = 3;
        for(int i=4;i<=n;i++){
            int max = 0;
            for(int j=1;j<=i/2;j++){
                int temp = rope[j]*rope[i-j];
                if(max<temp){
                    max = temp;
                }
            }
            rope[i]=max;
        }
        return rope[n];
    }
    public int cuttingRope1(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        if(n==4) return n;
        long res = 1;
        while (n>4){
            res = res*3;
            res = res%1000000007;
            n -= 3;
        }
        int resu = (int) (res*n%1000000007);
        return resu;
    }
    private long quickPow(int x, long n) {
        long res = 1;
        long tt = x;
        while (n != 0) {
            if ((n & 1) == 1) {
                res *= tt;
                res %= 1000000007;
            }
            tt *= tt;
            tt %= 1000000007;
            n /= 2;
        }
        return res;
    }

    //剑指 Offer 15. 二进制中1的个数
    public int hammingWeight(int n) {
        int count = 0;
        while (n!=0){
            ++count;
            n = n&(n-1);
        }
        return count;
    }


}
