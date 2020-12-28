package com.zcj.tree;

import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    //111. 二叉树的最小深度
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }
    Stack<TreeNode> s = new Stack<>();
    int min = Integer.MAX_VALUE;
    public int minDepth1(TreeNode root) {
        dfs(root);
        return min;
    }
    public void dfs(TreeNode root){
        if(root.left==null && root.right==null) {
            if (min > s.size()) {
                min = s.size();
            }
            return;
        }
        s.push(root);
        if(root.left!=null) dfs(root.left);
        if(root.right!=null) dfs(root.right);
        s.pop();
    }
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        // 计算左子树的深度
        int left = minDepth(root.left);
        // 计算右子树的深度
        int right = minDepth(root.right);
        // 如果左子树或右子树的深度不为 0，即存在一个子树，那么当前子树的最小深度就是该子树的深度+1
        // 如果左子树和右子树的深度都不为 0，即左右子树都存在，那么当前子树的最小深度就是它们较小值+1
        return (left==0||right==0)? left+right+1:Math.min(left,right)+1;
    }
}
