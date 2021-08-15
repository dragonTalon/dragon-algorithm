package com.dragon.talon.algorithm.arbitrary;

/**
 * @ClassName ReverseLinkTest
 * @Version 1.0
 * @Author dragon
 * @Date 2021/8/8 5:09 下午
 * @Description
 **/
public class ReverseLinkTest {
    public static void main(String[] args) {
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);
        test.next.next.next.next = new ListNode(5);
        test = reverseKGroup(test, 2);
        System.out.print(1);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k < 1) {
            return head;
        }
        ListNode check = head.next;
        int count = 1;
        while (count < k && null != check) {
            check = check.next;
            count++;
        }
        if (count == k) {
            ListNode[] temp = new ListNode[k];

            count = 0;
            ListNode cur = head;
            while (count < k) {
                temp[k - 1 - count] = cur;
                cur = cur.next;

                count++;
            }
            ListNode end = temp[0].next;
            ListNode start = new ListNode();
            ListNode b = start;
            for (int i = 0; i < k; i++) {
                start.next = temp[i];
                start = start.next;
            }
            if (end != null) {
                start.next = reverseKGroup(end, k);
            } else {
                start.next = end;
            }
            return b.next;
        } else {
            return head;
        }
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
