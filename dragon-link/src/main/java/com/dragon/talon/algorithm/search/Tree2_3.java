package com.dragon.talon.algorithm.search;

/**
 * 2-3 Tree
 * 从下到上插入
 * <p>
 * element  element
 * /      |      \
 * 二三树结构
 *
 * @author dragonboy
 */
public class Tree2_3 {
    private class Node<T extends Comparable> {
        private Node leftNode;

        private Node midNode;

        private Node rightNode;

        private T leftElement;

        private T rightElement;

        public Node() {
        }

        public Node(Node leftNode, Node midNode, Node rightNode, T leftElement, T rightElement) {
            this.leftNode = leftNode;
            this.midNode = midNode;
            this.rightNode = rightNode;
            this.leftElement = leftElement;
            this.rightElement = rightElement;
        }

        public Node(Node leftNode, Node rightNode, T leftElement, T rightElement) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.leftElement = leftElement;
            this.rightElement = rightElement;
        }

        public Node(T leftElement, T rightElement) {
            this.leftElement = leftElement;
            this.rightElement = rightElement;
        }

        public Node(T element) {
            this.leftElement = element;
        }


        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getMidNode() {
            return midNode;
        }

        public void setMidNode(Node midNode) {
            this.midNode = midNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public T getLeftElement() {
            return leftElement;
        }

        public void setLeftElement(T leftElement) {
            this.leftElement = leftElement;
        }

        public T getRightElement() {
            return rightElement;
        }

        public void setRightElement(T rightElement) {
            this.rightElement = rightElement;
        }

        private boolean isLeaf() {
            return this.leftNode == null && this.midNode == null && this.rightNode == null;
        }

        private boolean is2Node() {
            if (leftElement == null) {
                return false;
            }
            return rightElement == null;
        }

        private boolean is3Node() {
            if (leftElement == null) {
                return false;
            }
            return rightElement != null;
        }

        private boolean isBalanced() {
            if (isLeaf()) {
                return true;
            }
            if (leftNode.getLeftElement() != null && midNode.getLeftElement() != null) {
                //3Node
                if (rightElement != null) {
                    return leftElement != null;
                } else {
                    //2Node
                    return true;
                }
            }
            return false;
        }

        private <T extends Comparable> T replaceMax() {
            T max = null;

            if (!isLeaf()) {
                if (rightElement != null) {
                    max = (T) rightNode.replaceMax();
                } else {
                    max = (T) midNode.replaceMax();
                }
            } else {
                if (getRightElement() != null) {
                    max = (T) getRightElement();
                    setRightElement(null);
                } else {
                    max = (T) getLeftElement();
                    setLeftElement(null);
                }
            }
            //is not balanced ,change balance
            if (!isBalanced()) {

            }
            return max;
        }
    
        private void rebalanced(){
            
        }

    }
}
