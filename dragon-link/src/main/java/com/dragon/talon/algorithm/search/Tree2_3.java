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
    private Node root;

    private int size;

    public <T extends Comparable> void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (root == null) {
            root = new Node(value);
            root.setParent(null);
            return;
        }
        insert(value, root);
    }

    private <T extends Comparable> void insert(T value, Node node) {
        if (node.is3Node()) {
            int compare = value.compareTo(node.getRightElement());
            if (compare > 0) {
                if (node.getRightNode() != null) {
                    insert(value, node.getRightNode());
                } else {
                    spiltNode(node, new Node(value));
                }
            } else {
                if (node.getMidNode() != null) {
                    insert(value, node.midNode);
                } else {
                    spiltNode(node, new Node(value));
                    //节点切分
                }
            }
        } else if (node.is2Node()) {
            int leftCompare = value.compareTo(node.getLeftElement());
            if (leftCompare > 0) {
                if (node.getMidNode() != null) {
                    insert(value, node.midNode);
                } else {
                    node.setRightElement(value);
                }
            } else {
                if (node.getLeftNode() != null) {
                    insert(value, node.getLeftNode());
                } else {
                    Comparable leftElement = node.getLeftElement();
                    node.setLeftElement(value);
                    node.setRightElement(leftElement);
                }
            }
        }
       /* if (!node.isBalanced()) {
            //不平衡需要转变
        }*/
    }

    //切分的基础就是 left 和 right element不能为空 
    private <T extends Comparable> void spiltNode(Node node, Node value) {
        if (node.getParent() == null) {
            Node leftNode = new Node<>(node.getLeftElement());
            Node rightNode = new Node<>(node.getRightElement());
            Comparable leftElement = value.getLeftElement();
            if (leftElement.compareTo(node.leftElement) < 0) {
                this.root = leftNode;
                leftNode.setRightNode(rightNode);
                leftNode.setLeftNode(value);
                value.setParent(root);
                rightNode.setParent(root);
                rightNode.setRightNode(node.getRightNode());
                value.setLeftNode(node.getLeftNode());
            } else if (leftElement.compareTo(node.getRightElement()) > 0) {
                this.root = rightNode;
                rightNode.setLeftNode(leftNode);
                if (root.getMidNode() == null) {
                    root.setMidNode(value);
                } else {
                    rightNode.setRightNode(value);
                }
                leftNode.setParent(root);
                value.setParent(root);
                leftNode.setLeftNode(node.getLeftNode());
                if (node.getLeftNode() != null){
                    node.getLeftNode().setParent(leftNode);
                }
                if (node.getMidNode() != null) {
                    if (node.getMidNode().getLeftElement().compareTo(value.getLeftElement())<0){
                        leftNode.setMidNode(node.getMidNode());
                    }else if (node.getMidNode().getLeftElement().compareTo(value.getLeftElement())>0){
                        value.setLeftNode(node.getMidNode());
                    }
                }
                if (value.getLeftNode() == null){
                    value.setLeftNode(node.getRightNode());
                }
//                else {
//                    value.setMidNode(node.getRightNode());
//                }
            } else {
                this.root = value;
                root.setLeftNode(leftNode);
                root.setMidNode(rightNode);
                leftNode.setParent(root);
                rightNode.setParent(root);
                leftNode.setLeftNode(node.getLeftNode());
                rightNode.setRightNode(node.getRightNode());
            }
            if (node.getMidNode() != null) {
                if (this.root.getLeftElement().compareTo(node.getMidNode().getLeftElement()) > 0) {
                    this.root.getLeftNode().setMidNode(node.getMidNode());
                } else {
                    this.root.getRightNode().setLeftNode(node.getMidNode());
                }
            }
        } else {
            Node rootNode;
            Node leftNode = new Node<>(node.getLeftElement());
            Node rightNode = new Node<>(node.getRightElement());
            Node parent = node.getParent();
            Comparable leftElement = value.getLeftElement();

            if (leftElement.compareTo(node.getLeftElement()) < 0) {
                rootNode = leftNode;
                leftNode.setRightNode(rightNode);
                leftNode.setLeftNode(value);
                value.setParent(rootNode);
                rightNode.setParent(rootNode);
                rightNode.setRightNode(node.getRightNode());
                value.setLeftNode(node.getLeftNode());
            } else if (leftElement.compareTo(node.getRightElement()) > 0) {
                rootNode = rightNode;
                rightNode.setLeftNode(leftNode);
                rightNode.setMidNode(value);
                leftNode.setParent(rootNode);
                value.setParent(rootNode);
                leftNode.setLeftNode(node.getLeftNode());
                value.setRightNode(node.getRightNode());
            } else {
                rootNode = value;
                rootNode.setLeftNode(leftNode);
                rootNode.setMidNode(rightNode);
                leftNode.setParent(rootNode);
                rightNode.setParent(rootNode);
                leftNode.setLeftNode(node.getLeftNode());
                rightNode.setRightNode(node.getRightNode());
            }
            if (node.getMidNode() != null) {
                if (rootNode.getLeftElement().compareTo(node.getMidNode().getLeftElement()) > 0) {
                    rootNode.getLeftNode().setRightNode(node.getMidNode());
                } else {
                    rootNode.getRightNode().setLeftNode(node.getMidNode());
                }
            }
            //满的一套防范
            if (parent.is3Node()) {
                spiltNode(node.getParent(), rootNode);
            } else {
                if (rootNode.getLeftElement().compareTo(parent.getLeftElement()) > 0) {
                    parent.setRightElement(rootNode.getLeftElement());
                    parent.setMidNode(rootNode.getLeftNode());
                    parent.setRightNode(rootNode.getMidNode());
                    parent.getLeftNode().setParent(parent);
                    if (parent.getMidNode() != null) {
                        parent.getMidNode().setParent(parent);
                    }
                    if (parent.getRightNode() != null) {
                        parent.getRightNode().setParent(parent);
                    }
                } else if (rootNode.getLeftElement().compareTo(parent.getLeftElement()) < 0) {
                    parent.setRightElement(parent.getLeftElement());
                    parent.setLeftElement(rootNode.getLeftElement());
                    if (rootNode.getMidNode() != null) {
                        //没想好怎么处理
                    } else {
                        parent.setMidNode(parent.getLeftNode());
                        parent.setLeftNode(rootNode.getLeftNode());
                    }
                }
            }
        }

    }

    private class Node<T extends Comparable> {
        private Node leftNode;

        private Node midNode;

        private Node rightNode;

        private Node parent;

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

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
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

        public boolean isLeaf() {
            return this.leftNode == null && this.midNode == null && this.rightNode == null;
        }

        public boolean is2Node() {
            if (leftElement == null) {
                return false;
            }
            return rightElement == null;
        }

        public boolean is3Node() {
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
    }

    public static void main(String[] args) {
        Tree2_3 tree2_3 = new Tree2_3();
        tree2_3.add("A");
        tree2_3.add("C");
        tree2_3.add("B");
        tree2_3.add("E");
        tree2_3.add("D");
       
        tree2_3.add("F");
        tree2_3.add("G");
        tree2_3.add("H");
    }
}
