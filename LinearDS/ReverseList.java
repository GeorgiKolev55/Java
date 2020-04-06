package implementations;

import antlr.collections.Stack;
import interfaces.LinkedList;

import java.sql.Array;
import java.util.*;

public class ReverseList<E>  extends ArrayDeque<E> {
    private Object[] data;
    private final int CAPACITY = 7;
    private int startEl;
    private int lastEl;
    private int size;

    public ReverseList() {

        this.data = new Object[CAPACITY];
        int midEl = data.length/2;
        this.lastEl=startEl=midEl;
    }
    public void Add(E element){
        if (this.size<data.length){
            if (data.length==0){
                data[startEl]=element;
                size++;
            }
            else {
                data[++lastEl]=element;
                size++;
            }
        }
        else {doubleArrSize();}
    }

    private Object[] doubleArrSize() {

        int newCapacity = data.length*2+1;
        Object[] newArray =new Object[newCapacity];
        int midEl = newCapacity/2;
        int begin = midEl-this.size/2;
        int el =startEl;
        for (int i = begin; startEl<= lastEl;i++){
              newArray[i]=data[startEl++];
        }
        this.startEl=begin;
        this.lastEl=startEl+size-1;
        return newArray;

    }
    public int Size(){
        return this.size;
    }
    public int Capacity(){
        return data.length;
    }
    public E get(int index){
        return(E) data[index];

    }
    public Iterator<E> itreator(){
         return new Iterator<E>() {
             private  int index = startEl;
             @Override
             public boolean hasNext() {
                 return index<=lastEl;
             }

             @Override
             public E next() {
                 return (E) data[index++];
             }
         };
    }
}
