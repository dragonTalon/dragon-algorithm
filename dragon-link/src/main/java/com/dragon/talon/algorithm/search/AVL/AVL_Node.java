package com.dragon.talon.algorithm.search.AVL;

/**
 * 平衡二叉树节点
 * 
 * @author dragonboy 
 */
public class AVL_Node<T extends Comparable> {
    private T element;
    
    private AVL_Node<T> left;
    
    private AVL_Node<T> right;
    
    private int height;

    public AVL_Node() {}

    public AVL_Node(T element) {
        this.element = element;
    }

    public AVL_Node(T element, AVL_Node left, AVL_Node right, int height) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public AVL_Node getLeft() {
        return left;
    }
    
    public void setLeft(AVL_Node<T> left) {
        this.left = left;
    }

    public AVL_Node<T> getRight() {
        return right;
    }

    public void setRight(AVL_Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
