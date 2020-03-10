package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 4;
    private Object[] elements;
    private int currNumOfElements;
    private int capacity;

    public ArrayList() {
        capacity=INITIAL_SIZE;
        this.elements=new Object[capacity];

        currNumOfElements=0;
    }

    @Override
    public boolean add(E element){
        if (this.currNumOfElements ==this.capacity) {
           resize();
        }
        elements[currNumOfElements++]=element;
        return true;
    }

    private void resize() {
       this.capacity*=2;
       Object[] tmp = new Object[capacity];
       for (int i =0;i< elements.length;i++){
           tmp[i]=elements[i];
       }
       elements=tmp;
    }

    @Override
    public boolean add(int index, E element) {
        if (!(0<=index || index<this.currNumOfElements)) {
          return false;
        }

       shiftRight(index);
        currNumOfElements++;
        elements[index]=element;
        return true;
    }

    private void shiftRight(int index) {
        for (int i = currNumOfElements-1;i>=index;i--){
            elements[i+1]=elements[i];

        }
    }

    @Override
    public E get(int index) {
        if (index<0 || index> currNumOfElements-1) {
            throw new IndexOutOfBoundsException();
        }

        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (index<0 || index> currNumOfElements-1) {
            throw new IndexOutOfBoundsException("Not");
        }
        var prevElem = elements[index];
        elements[index]=element;
        return (E) prevElem;
    }

    @Override
    public E remove(int index) {
        if (index<0 || index> currNumOfElements-1) {
            throw new IndexOutOfBoundsException();
        }
        var retElem = elements[index];
        shiftLeft(index);

        currNumOfElements--;
        return (E) retElem;
    }

    private void shiftLeft(int index) {
        for (int i = index; i< elements.length-1;i++) {
            elements[i]=elements[i+1];
        }

    }

    @Override
    public int size() {
        return this.currNumOfElements;
    }

    @Override
    public int indexOf(E element) {

        return findElement(element);
    }

    private int findElement(E element) {
        for (int i = 0 ;i<currNumOfElements;i++){
            if (elements[i].equals(element)){
                return i;
            }

        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
       for (int i = 0;i<elements.length;i++){
           if (element.equals(elements[i])){return true;}

       }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.currNumOfElements ==0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int index=0;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}
