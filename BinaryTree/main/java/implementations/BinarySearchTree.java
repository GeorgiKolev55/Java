package implementations;

import interfaces.AbstractBinarySearchTree;
import interfaces.AbstractBinaryTree;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;
    private Node<E> leftChild;
    private Node<E> rightChild;

    public BinarySearchTree(){}
    public BinarySearchTree(Node<E> root){
        this.copy(root);
    }

    private void copy(Node<E> node) {
        if (node!=null) {
            this.insert(node.value);
            this.copy(node.leftChild);
            this.copy(node.rightChild);
        }
    }

    @Override
    public void insert(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.root==null){
            root=newNode;
        }else {
             Node<E> current = this.root;
            Node<E> prev =current;
             while (current!=null){
                 prev=current;
                 if (isLess(element,current.value)){
                     current=current.leftChild;
                 }else if(isGreater(element,current.value)){
                     current=current.rightChild;
                 }else if (areEqual(element,current.value)){
                     return;
                 }
             }
             if (isLess(element,prev.value)){
                 prev.leftChild=newNode;
             }else  if(isGreater(element,prev.value)){
                 prev.rightChild=newNode;
             }
        }
    }

    private boolean isLess(E element, E value) {
        return element.compareTo(value)<0;
    }
    private boolean isGreater(E element, E value) {
        return element.compareTo(value)>0;
    }
    private boolean areEqual(E element, E value) {
        return element.compareTo(value)==0;
    }
    @Override
    public boolean contains(E element) {
        Node<E> current= this.root;
        while (current!=null){
            if (isLess(element,current.value)){current=current.leftChild;}
            else if (isGreater(element,current.value)){current=current.rightChild;}
            else if (areEqual(element,current.value)){return true;}
        }
        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        AbstractBinarySearchTree<E> abt= new BinarySearchTree<>();
        Node<E> newNode = new Node<>(element);
        Node<E> curr = this.root;
        while (curr!=null){
            if (isLess(element,curr.value)){
                curr=curr.leftChild;
            }
            else if(isGreater(element,curr.value)){
                curr=curr.rightChild;
            }
            else if(areEqual(element,curr.value)){
                return new BinarySearchTree<>(curr);
            }

        }
        return abt;
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.rightChild;
    }

    @Override
    public E getValue() {

        return null;
    }
}
