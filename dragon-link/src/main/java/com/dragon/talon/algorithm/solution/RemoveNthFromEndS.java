package com.dragon.talon.algorithm.solution;

/**
 * @ClassName RemoveNthFromEndS
 * @Version 1.0
 * @Author dragon
 * @Date 2021/9/5 4:03 下午
 * @Description:给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 **/
public class RemoveNthFromEndS {
    public static void main(String[] args) {
        final ListNode one = new ListNode(1);
        final ListNode tow = new ListNode(2);
        final ListNode three = new ListNode(3);
        final ListNode four = new ListNode(4);
        final ListNode five = new ListNode(5);
        one.next = tow;
        tow.next = three;
        three.next = four;
        four.next = five;
        final ListNode listNode = new RemoveNthFromEndS().removeNthFromEnd(one, 5);
        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n < 1 || head == null) {
            return head;
        }
        final int i = removeNodeToEnd(head, n);
        if (i == n) {
            head = head.next;
        }
        return head;
    }

    private int removeNodeToEnd(ListNode node, int n) {
        if (node == null) {
            return 0;
        }
        int index = removeNodeToEnd(node.next, n);
        if (index == n) {
            node.next = node.next.next;
        }
        return index + 1;
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
