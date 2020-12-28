package com.zcj.jianzhioffer;

import com.zcj.learnBili.Tree.Tree;

public class Session3 {
    public static void main(String[] args) {
//    System.out.println(new Session3().myPow(2.0, 10));
        System.out.println(new Session3().isMatch("aa", ".*c"));
    }

    //剑指 Offer 16. 数值的整数次方
    public double myPow(double x, int n) {
        if(x==0 ) return 0;
        double result = 1;
        long b = n;
        if(n<0){
            x=1/x;
            b=-b;
        }
        while (b>0){
            if((b&0x1)==1) result *=x;
            x *=x;
            b>>=1;
        }
        return result;
    }

    //剑指 Offer 18. 删除链表的节点
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteNode(ListNode head, int val) {
        if(head==null) return head;
        if(head.val==val){
            head = head.next;
            return head;
        }
        ListNode temp = head;
        while (temp.next!=null){
            if(temp.next.val==val){
                if(temp.next.next==null){
                    temp.next = null;
                }else {
                    temp.next = temp.next.next;
                }
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    //剑指 Offer 19. 正则表达式匹配
    public boolean isMatch(String s, String p) {
        if(s.equals("")||p.equals("")) return false;
        return isMatchCore(s,p,0,0);
    }
    public boolean isMatchCore(String s, String p,int sl,int pl){
        if(sl == s.length() && pl == p.length()) return true;
        if(sl!=s.length() && pl==p.length()) return  false;
        if(sl>s.length() || pl>p.length()) return false;
        if(pl+1<p.length() && p.charAt(pl+1)=='*'){
            if(p.charAt(pl)==s.charAt(sl) ||(p.charAt(pl)=='.' && sl!=s.length())){
                return isMatchCore(s,p,sl+1,pl+2)
                        || isMatchCore(s,p,sl+1,pl)
                        || isMatchCore(s,p,sl,pl+2);
            }else{
                return isMatchCore(s,p,sl,pl+2);
            }
        }
        if(sl<s.length()&&pl<p.length())
            if(s.charAt(sl)==p.charAt(pl)||(p.charAt(pl)=='.' && sl!=s.length()))
                return isMatchCore(s,p,sl+1,pl+1);
        return false;
    }

    //剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
    public int[] exchange(int[] nums) {
        if(nums==null||nums.length==1) return nums;
        int pre = 0;
        int after = nums.length-1;
        while (pre<after){
            while (pre<nums.length && (nums[pre]& 0x1)!=0){
                pre++;
            }
            while (after>0&&(nums[after]& 0x1)==0){
                after--;
            }
            if(pre<after){
                int tem = nums[pre];
                nums[pre] = nums[after];
                nums[after] = tem;
            }
        }
        return nums;
    }


    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null||k<=0) return null;
        ListNode pre = head;
        ListNode after = head;
        int p = k-1;
        while (p>0){
            if(after.next!=null){
                after = after.next;
            }else{
                return null;
            }
            --p;
        }
        while (after.next!=null){
            after = after.next;
            pre = pre.next;
        }
        return pre;
    }

    //剑指 Offer 24. 反转链表
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode newHead = head;
        ListNode curNode = head.next;
        ListNode nextNode = curNode.next;
        newHead.next = null;
//        if(nextNode==null){
//            curNode.next = newHead;
//            newHead = curNode;
//            return newHead;
//        }
        while (nextNode!=null){
            curNode.next = newHead;
            newHead = curNode;
            curNode = nextNode;
            nextNode = nextNode.next;
        }

        curNode.next = newHead;
        newHead = curNode;
        return newHead;
    }

    //剑指 Offer 25. 合并两个排序的链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode newHead = null;
        if(l1.val<l2.val){
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next,l2);
        }else{
            newHead = l2;
            newHead.next = mergeTwoLists(l1,l2.next);
        }
        return newHead;
    }

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        boolean result = false;
        if(A!=null&&B!=null){
            if(A.val==B.val){
                result = isSubStructureCore(A,B);
            }
            if(!result){
                result = isSubStructure(A.left,B);
            }
            if(!result){
                result = isSubStructure(A.right,B);
            }
        }
        return result;
    }
    public boolean isSubStructureCore(TreeNode a,TreeNode b){
        if(b==null) return true;
        if(a==null) return false;
        if(a.val!=b.val){
            return false;
        }else{
            return isSubStructure(a.left,b.left)&&
                    isSubStructureCore(a.right,b.right);
        }

    }


}
