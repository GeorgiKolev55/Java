package implementations;

import com.sun.source.tree.Tree;
import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E value;
    private BinaryTree<E> leftChild;
    private BinaryTree<E> rightChild;

    public BinaryTree(E value,BinaryTree<E> leftChild,BinaryTree<E> rightChild)
    {
        this.value = value;
        this.leftChild=leftChild;
        this.rightChild=rightChild;
    }

    @Override
    public E getKey() {
        return this.value;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return rightChild;
    }

    @Override
    public void setKey(E key) {
        this.value=key;

    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder sb = new StringBuilder();
        space(indent);
        sb.append(space(indent));
        sb.append(getKey());

        if (getLeft()!=null){
          String prouder = this.getLeft().asIndentedPreOrder(indent+2);
            sb.append(System.lineSeparator());sb.append(prouder);
        }
        if (getRight()!=null){
           String prouder = this.getRight().asIndentedPreOrder(indent+2);
            sb.append(System.lineSeparator());sb.append(prouder);
        }
        return sb.toString();
    }

    private String space(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i =0; i< indent;i++){
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> prouder = new ArrayList<>();
        prouder.add(this);
        if (this.getLeft()!=null){
          var cut =  this.getLeft().preOrder();
          prouder.addAll(cut);
        }
        if (this.getRight()!=null){
            var cuy =this.getRight().preOrder();
            prouder.addAll(cuy);
        }
        return prouder;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> preorder = new ArrayList<>();

        if (this.getLeft()!=null){
            preorder.addAll(this.getLeft().inOrder());

        }
        preorder.add(this);
        if (this.getRight()!=null){
            preorder.addAll(this.getRight().inOrder());

        }

        return preorder;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (getLeft()!=null){
            result.addAll(getLeft().postOrder());
        }
        if (getRight()!=null){
            result.addAll(getRight().postOrder());
        }

        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (getLeft()!=null){
            getLeft().forEachInOrder(consumer);
        }
        consumer.accept(getKey());
        if (getRight()!=null){
            getRight().forEachInOrder(consumer);
        }
    }
}
