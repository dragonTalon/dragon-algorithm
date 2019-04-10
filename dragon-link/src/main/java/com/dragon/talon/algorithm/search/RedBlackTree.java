package com.dragon.talon.algorithm.search;

/**
 * 红黑树
 * 是一个平衡的二叉树
 * 他的演变是从2-3树演变成的红黑树
 *
 * @author dragonboy
 */
public class RedBlackTree<T extends Comparable> {
    public static final boolean BLACK = false;

    public static final boolean RED = true;
    private RedBlackNode root;

    private boolean isRed(RedBlackNode node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private RedBlackNode rotateLeft(RedBlackNode h) {
        RedBlackNode x = h.right;
        h.setRight(x.left);
        x.setLeft(h);
        x.setColor(h.getColor());
        h.setColor(RED);
        x.setSize(h.getSize());
        h.setSize(1 + size(h.left) + size(h.right));
        return x;
    }

    private void filpColors(RedBlackNode h) {
        h.setColor(RED);
        h.left.setColor(BLACK);
        h.right.setColor(BLACK);
    }

    private RedBlackNode rotateRight(RedBlackNode h) {
        RedBlackNode x = h.left;
        h.setLeft(x.right);
        x.setRight(h);
        x.setColor(h.getColor());
        h.setColor(RED);
        x.setSize(h.getSize());
        h.setSize(1 + size(h.left) + size(h.right));
        return x;
    }

    private int size(RedBlackNode h) {
        if (h == null){
            return 0;
        }
        int sum = 0;
        if (h.left != null) {
            sum += size(h.left);
        }
        if (h.right != null) {
            sum += size(h.right);
        }
        return sum;
    }

    public void add(T value) {
        root = put(root, value);
        root.setColor(BLACK);
    }

    private RedBlackNode put(RedBlackNode h, T val) {
        if (h == null) {
            return new RedBlackNode(RED, val, 1);
        }
        int cmp = val.compareTo(h.getValue());
        if (cmp < 0) {
            h.setLeft(put(h.left, val));
        } else if (cmp > 0) {
            h.setRight(put(h.right, val));
        } else {
            h.value = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            filpColors(h);
        }
        h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return h;
    }

    private class RedBlackNode<T extends Comparable> {
        private RedBlackNode left;
        private RedBlackNode right;
        private boolean color = BLACK;
        private T value;
        private int size;


        public RedBlackNode(boolean color, T value, int size) {
            this.color = color;
            this.value = value;
            this.size = size;
        }

        public RedBlackNode getLeft() {
            return left;
        }

        public void setLeft(RedBlackNode left) {
            this.left = left;
        }

        public RedBlackNode getRight() {
            return right;
        }

        public void setRight(RedBlackNode right) {
            this.right = right;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add("A");
        tree.add("C");
        tree.add("B");
        tree.add("E");
        tree.add("D");

        tree.add("F");
        tree.add("G");
        tree.add("H");
    }
}
