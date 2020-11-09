package com.zcj.array;
//2.两数相加
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dumyNode = new ListNode(0);
        int x=0,y=0,carry=0,sum = 0;
        ListNode curr = dumyNode;
        while(l1!=null || l2!=null){
            x = l1==null? 0 : l1.val;
            y = l2==null? 0 : l2.val;
            sum = x + y + carry;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }

        }
        if(carry>0){
            curr.next = new ListNode(carry);
        }

        return dumyNode.next;
    }
}