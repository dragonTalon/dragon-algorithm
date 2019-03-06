package com.dragon.talon.algorithm.link.singleLinkList;

import com.dragon.talon.algorithm.link.DragonLink;

/**
 * 单向链表
 *
 * @param <T>
 * @author dragonboy
 */
public class DragonSingleLinkList<T> implements DragonLink<T> {
    private Snode<T> head;

    private int size = 0;

    public DragonSingleLinkList(Snode<T> head) {
        this.head = head;
    }

    public DragonSingleLinkList() {
    }

    public DragonSingleLinkList(T[] values) {
        this.head = null;
        if (values != null && values.length > 0) {
            Snode template = null;
            this.size++;
            for (int i = 0; i < values.length; i++) {
                if (i == 0) {
                    this.head = new Snode<T>(values[i]);
                    template = this.head;
                    continue;
                }
                template.setNext(new Snode(values[i]));
                template = template.getNext();
                size++;
            }
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        if (head.getData() == null) {
            return true;
        }
        return false;
    }


    public int length() {
        return size;
    }

    public T get(int index) {
        if (!checkElementIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("index more than List size");
        }
        return findNode(index).getData();
    }

    public void set(int index, T data) {
        if (!checkElementIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("index more than List size");
        }
        Snode<T> snode = findNode(index);
        snode.setData(data);
    }

    public void add(int index, T data) {
        if (!checkElementIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("index more than List size");
        }
        Snode node = new Snode(data);
        size++;
        if (index == 0){
            node.setNext(this.head);
            this.head = node;
            return;
        }
        Snode<T> snode = findNode(index-1);
        Snode next = snode.getNext();
        snode.setNext(node);
        node.setNext(next);
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is not null");
        }
        Snode<T> tSnode = new Snode<T>(data);
        if (size == 0) {
            this.head = tSnode;
        }else {
            Snode<T> node = findNode(size-1);
            node.setNext(tSnode);
        }
        size++;
    }

    public T remove(int index) {
        if (!checkElementIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("index more than List size");
        }
        Snode<T> node = findNode(index - 1);
        Snode<T> next = node.getNext();
        node.setNext(next.getNext());
        return next.getData();
    }

    public boolean removeAll(T data) {
        boolean flag = false;
        if (this.head == null) {
            return false;
        }
        Snode temp = this.head;
        if (head.getData().equals(data)) {
            this.head = head.getNext();
            flag = true;
            size--;
        }
        while (temp.getNext() != null) {
            Snode next = temp.getNext();
            if (next.getData().equals(data)) {
                temp.setNext(next.getNext());
                flag = true;
                size--;
                continue;
            }
            temp = next;
        }
        return true;
    }

    public void clear() {
        this.head = null;
    }

    public boolean contains(T data) {
        Snode snode = this.head;
        while (snode != null){
            if (snode.getData().equals(data)){
                return true;
            }
            snode = snode.getNext();
        }
        return false;
    }
    
    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        Snode snode = this.head;
        while (snode != null){
            stringBuffer.append(snode.getData()+"\t");
            snode = snode.getNext();
        }
        return stringBuffer.toString();
    }
    
    private boolean removeElement(Snode snode,T data){
        if (snode.getData().equals(data)) {
            this.head = head.getNext();
            size--;
            return true;
        }
        return false;
    }

    private boolean checkElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private Snode<T> findNode(int index) {
        if (index <=0) {
            return head;
        }
        Snode node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public static void main(String[] args) {
        DragonSingleLinkList<Integer> linkList = new DragonSingleLinkList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(3);
        linkList.add(3,5);
        System.out.println("所有的值 ："+linkList.toString());
        System.out.println("单向链表是否为空 ：" + linkList.isEmpty());
        System.out.println("获取第二个数据大小 ："+linkList.get(1));
        System.out.println("删除前大小 ："+linkList.length());
        linkList.removeAll(3);
        System.out.println("删除之后的数据大小 ："+linkList.length());
        linkList.set(1,4);
        System.out.println("修改后的第二个数据值 ："+ linkList.get(1));
        System.out.println("所有的值 ："+linkList.toString());
        System.out.println("删除的值为 ："+linkList.remove(0));
        System.out.println("所有的值 ："+linkList.toString());
        System.out.println("含有5 嘛？答案 ："+linkList.contains(5));
        System.out.println("含有3 嘛？答案 ："+linkList.contains(3));
        linkList.clear();
        System.out.println("单向链表是否为空 ：" + linkList.isEmpty());
        
    }
}
