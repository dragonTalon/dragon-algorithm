package com.dragon.talon.algorithm.arbitrary;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(9);
        root.left =new TreeNode(20);
        root.left.right = new TreeNode(15);
        root.left.left = new TreeNode(7);
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, res, 0);

    }

    private static void traversal(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }

        if ((level & 1) == 1) {
            res.get(level).add(0, root.val);
        } else {
            res.get(level).add(root.val);
        }

        traversal(root.left, res, level + 1);
        traversal(root.right, res, level + 1);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
