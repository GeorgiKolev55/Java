package implementations;

import interfaces.AbstractTree;

import javax.swing.*;
import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> childrens;

     // syzdavame korena na dyrvoto

    public Tree(E value,Tree<E>... subtrees){ //syzdavame poredica... ot dyrveta(deca na korena).
        this.value=value;
        this.parent=null;
        this.childrens=new ArrayList<>(); //list v koito gi pazim.
        for (Tree<E> subtree : subtrees){
            this.childrens.add(subtree);
            subtree.parent=this;
        }

    }

    @Override
    public List<E> orderBfs() { //Breadth first search - 1st visit th neighbor nodes, then
                               // the neighbors of neighbors etc.
        List<E> result = new ArrayList<>();
        if (this.value==null){return result;}
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()){
            Tree<E> current = queue.poll();
            result.add(current.value);
            for (Tree<E> child : current.childrens){
                queue.offer(child);
            }
        }
        return result;
    }

    @Override
    public List<E> orderDfs() { //Depth first search
        List<E> order = new ArrayList<>();
        this.dfs(this,order);
        return order;
        }

    private void dfs(Tree<E> eTree, List<E> order) {
        for (Tree<E> child : eTree.childrens){
             this.dfs(child,order); //recursia
        }
        order.add(eTree.value);
    }


    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = findBfs(parentKey);
        if (search.equals(null) ){throw new IllegalArgumentException();}
        search.childrens.add(child);
        child.parent=search;
    }
     public Tree<E> findBfs(E parentKey){

         Deque<Tree<E>> queue = new ArrayDeque<>();
         queue.offer(this);
         while (!queue.isEmpty()){
             Tree<E> current = queue.poll();
             if (current.value.equals(parentKey)){
                 return current;
             }
             for (Tree<E> child : current.childrens){
                 queue.offer(child);
             }
         }
         return null;
     }
    public Tree<E> find(Tree<E> eTree, E parentKey) {

        if (eTree.value.equals(parentKey)){return eTree;}
        for (Tree<E> child : eTree.childrens){
            Tree<E> found = this.find(child,parentKey);
            if (found!= null){return found;}
        }
        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
         Tree<E> nodeToRemove = find(this,nodeKey);
         if (nodeToRemove.equals(null)){
             throw new IllegalArgumentException();
         }
         for (Tree<E> child : nodeToRemove.childrens){
             child.parent=null; //kazvame za vsqko subchild ,che negoviq roditel e null
         }
         nodeToRemove.childrens.clear();
         Tree<E> parent = nodeToRemove.parent; //namirame roota
        if (parent!=null) {
            parent.childrens.remove(nodeToRemove); //premahvame ot roota dadeniq child
        }
        nodeToRemove.value=null;
    }



    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode= findBfs(firstKey);
        Tree<E> secondNode = findBfs(secondKey);
        if (firstNode == null || secondNode == null){
            throw new IllegalArgumentException();
        }
        Tree<E> firstParent = firstNode.parent;
        Tree<E> secParent = secondNode.parent;
        if (firstParent==null){
            this.value=secondNode.value;
            this.parent=null;
            this.childrens =secondNode.childrens;
            return;
        }else if (secParent==null){
            this.value=firstNode.value;
            this.parent=null;
            this.childrens =firstNode.childrens;
            return;
        }

        firstNode.parent=secParent;
        secondNode.parent=firstParent;

        int i = firstParent.childrens.indexOf(firstNode);
        int ie =secParent.childrens.indexOf(secondNode);
        firstParent.childrens.set(i,secondNode);


        secParent.childrens.set(ie,firstNode);



    }
}



