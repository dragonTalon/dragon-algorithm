package com.dragon.talon.algorithm.search.AVL;

import java.util.Optional;

/**
 * 平衡二叉树
 *
 * @author dragonboy
 */
public class AVL_Tree<T extends Comparable> {
    private AVL_Node<T> root;

    public AVL_Tree() {
    }

    public AVL_Tree(AVL_Node root) {
        this.root = root;
    }

    public AVL_Node getRoot() {
        return root;
    }

    public void setRoot(AVL_Node root) {
        this.root = root;
    }

    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is not null");
        }
        this.root = insert(data, root);
    }

    private AVL_Node<T> insert(T data, AVL_Node<T> root) {
        if (root == null) {
            root = new AVL_Node(data);
        }
        //判断插入位置
        int compare = data.compareTo(root.getElement());
        if (compare != 0) {
            //left node
            if (compare < 0) {
                root.setLeft(insert(data, root.getLeft()));
                if (abs(height(root.getLeft()) - height(root.getRight())) >= 2) {
                    //ll:由于在*a的左子树根节点的左子树上插入节点，
                    // *a的平衡因子由1增至2，致使以*a为根的子树失去平衡，则需进行一次右旋转操作；
                    if (data.compareTo(root.getLeft().getElement()) < 0) {
                        root = singleLeftRotate(root);
                    }
                    //lr:由于在*a的左子树根节点的右子树上插入节点，*a的平衡因子由1增至2，
                    // 致使以*a为根的子树失去平衡，则需进行两次旋转（先左旋后右旋）操作。
                    else {
                        root = leftToRightRotate(root);
                    }
                }


            }
            //right node
            else {
                root.setRight(insert(data, root.getRight()));
                if (abs(height(root.getLeft()) - height(root.getRight())) >= 2) {
                    //rr:由于在*a的右子树根节点的右子树上插入节点，
                    // *a的平衡因子由-1变为-2，致使以*a为根的子树失去平衡，则需进行一次左旋转操作；
                    if (data.compareTo(root.getRight().getElement()) > 0) {
                        root = singleRightRotate(root);
                    }
                    //rl:由于在*a的右子树根节点的左子树上插入节点，*a的平衡因子由-1变为-2，
                    // 致使以*a为根的子树失去平衡，则需进行两次旋转（先右旋后左旋）操作。
                    else {
                        root = rightToLeftRotate(root);
                    }
                }
            }
        }
        //重新计算高度
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        return root;
    }

    public Boolean contain(T value){
        if (value == null){
         throw new IllegalArgumentException("value not null");   
        }
       
        return search(value,this.root);
    }
    
    private boolean search(T value,AVL_Node node){
        if (node == null){
            return false;
        }
        if (node.getElement().compareTo(value)==0){
            return true;
        }
        
        if (node.getElement().compareTo(value)>0){
            if (!Optional.ofNullable(node.getLeft()).isPresent()){
                return false;
            }
            return search(value,node.getLeft());
        }else {
            if (!Optional.ofNullable(node.getRight()).isPresent()){
                return false;
            }
            return search(value,node.getRight());
        }
    }
    
   
    
    
    /**
     * ll表示的是插入左边  左边重了
     * ll ： right rotate 右旋
     */
    private AVL_Node<T> singleLeftRotate(AVL_Node root) {
        AVL_Node left = root.getLeft();
        if (Optional.ofNullable(left.getRight()).isPresent()) {
            root.setLeft(left.getRight());
        }
        left.setRight(root);
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        left.setHeight(Math.max(height(left.getLeft()), height(left.getRight())) + 1);
        return left;
    }

    /**
     * rr:表示的是插入右边 右边重了
     * rr:left rotate 左旋
     */
    private AVL_Node<T> singleRightRotate(AVL_Node root) {
        AVL_Node right = root.getRight();
        if (Optional.ofNullable(right.getLeft()).isPresent()) {
            root.setRight(right.getLeft());
        }
        right.setLeft(root);
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        right.setHeight(Math.max(height(right.getLeft()), height(right.getRight())) + 1);
        return right;
    }

    /**
     * rl:表示的是插入在右边 左边重了
     * rl: 由于在*a的右子树根节点的左子树上插入节点，*a的平衡因子由-1变为-2，
     * 致使以*a为根的子树失去平衡，则需进行两次旋转（先右旋后左旋）操作。
     */
    private AVL_Node rightToLeftRotate(AVL_Node root) {
        root.setRight(singleLeftRotate(root));
        return singleRightRotate(root);
    }

    /**
     * lr:表示的是插入在左边  右边重了
     * lr:由于在*a的左子树根节点的右子树上插入节点，*a的平衡因子由1增至2，
     * 致使以*a为根的子树失去平衡，则需进行两次旋转（先左旋后右旋）操作。
     */
    private AVL_Node leftToRightRotate(AVL_Node root) {
        root.setLeft(singleRightRotate(root));
        return singleLeftRotate(root);
    }

    private int abs(int value) {
        return value > 0 ? value : -value;
    }

    private int height(AVL_Node<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    public static void main(String[] args) {
        AVL_Tree<Integer> tree = new AVL_Tree();
        for (int i = 1; i < 32; i++) {
            tree.put(i);
        }
        System.out.println(tree.contain(32));
    }
}
