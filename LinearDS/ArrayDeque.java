package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {


    private final int INITIAL_CAPACITY=7;
    private int size;
    private int headIndex;
    private int tailIndex;
    private Object[] arrayElements;
    public ArrayDeque() {

        this.arrayElements = new Object[INITIAL_CAPACITY];
        int middleElement = INITIAL_CAPACITY/2;
        headIndex=tailIndex=middleElement;
    }

    @Override
    public void add(E Element) {
            addLast(Element);
        }

    private Object[] grow() {
        int newArrCapacity = this.arrayElements.length * 2 +1;
        Object[] newArr =new Object[newArrCapacity];
        int midElem = newArrCapacity/2;
        int begin = midElem - this.size/2;
        int index = this.headIndex;
        for (int i =begin;index<=this.tailIndex ; i++){
            newArr[i]=arrayElements[index++];
        }
        this.headIndex=begin;
        this.tailIndex=this.headIndex + size -1;
        return newArr;
    }



    @Override
    public void offer(E element) {
     addLast(element);
    }

    @Override
    public void addFirst(E element) {
          if (this.size==0){addLast(element);}

          else {
              if (this.headIndex==0){this.arrayElements = grow();}
              arrayElements[--this.headIndex]=element;size++;}
    }

    @Override
    public void addLast(E element) {
        if (this.size==0){
            this.arrayElements[this.headIndex]=element;
            size++;
        }
        else {
            if (this.tailIndex==this.arrayElements.length-1) {
                this.arrayElements=grow();
            }
            this.arrayElements[++this.tailIndex]=element;
            size++;
        }

    }

    @Override
    public void push(E element) {
      addLast(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = this.headIndex+index;
        if (realIndex>=this.headIndex && realIndex<=this.tailIndex) {
            if (realIndex-headIndex<tailIndex-realIndex){
                insertAndShiftLeft(realIndex-1,element);
            }else {
                insertAndShiftRight(realIndex,element);
            }
            arrayElements[index]=element;
        }
        else {throw new IndexOutOfBoundsException();}

    }

    private void insertAndShiftRight(int index,E element) {
        Object last = arrayElements[tailIndex];
        for(int i = this.tailIndex;i>index;i--){
            arrayElements[i]=arrayElements[i-1];
        }
        size++;
        this.addLast((E)last);
        arrayElements[index]=element;
    }

    private void insertAndShiftLeft(int index,E element) {
        Object firstEl = arrayElements[headIndex];
        for (int i =headIndex;i<=index;i++){
            arrayElements[i]=arrayElements[i+1];
        }
        size++;
        arrayElements[index]=element;
        addFirst((E) firstEl);
    }

    @Override
    public void set(int index, E element) {
        int realIndex = headIndex+index;
        if (realIndex>=this.headIndex && realIndex<=this.tailIndex) {
            insert(realIndex, element);
            size++;
        }
        else {throw new IndexOutOfBoundsException();}
    }

    @Override
    public E peek() {
        if(this.size!=0){
          return (E) this.arrayElements[headIndex];
        }
        return null;
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public E get(int index) {
        int real = headIndex+index;
        if (real>=this.headIndex && real<=this.tailIndex)
        {return (E) arrayElements[real];}
        else {throw new IndexOutOfBoundsException();}
    }

    @Override
    public E get(Object object) {
        if (isEmpty()){return null;}
        for (int i = headIndex;i<=tailIndex;i++){
            if (arrayElements[i].equals(object)){
               return (E) arrayElements[i];
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        int realIndex = this.headIndex+index;
        if (realIndex>=this.headIndex && realIndex<=this.tailIndex){
            Object removedE = arrayElements[realIndex];
             arrayElements[realIndex]=null;
             for (int i = realIndex;i>0;i--){
                 arrayElements[i]=arrayElements[i-1];
             }
             this.size--;
             this.headIndex++;
             return (E) removedE;
        }
        else {throw new IndexOutOfBoundsException();}
    }

    @Override
    public E remove(Object object) {
        if (isEmpty()){return null;}
        for (int i = headIndex;i<=tailIndex;i++){
            if (arrayElements[i].equals(object)){
                Object toReturn =arrayElements[i];
                arrayElements[i]=null;

                for (int j = i;i<tailIndex;i++){
                    arrayElements[j]=arrayElements[j+1];
                }
                removeLast();
                return (E) toReturn;
            }
        }
       return null;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()){
            Object element = arrayElements[headIndex];
            arrayElements[headIndex]=null;
            this.headIndex++;
            this.size--;
            return (E)element;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()){
            Object elem = arrayElements[tailIndex];
            arrayElements[tailIndex]=null;
            tailIndex--;
            this.size--;
            return (E) elem;
        }

        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return arrayElements.length;
    }

    @Override
    public void trimToSize() {
        Object[] shriekedArr = new Object[size];
        int index=0;
        for (int i =headIndex;i<=tailIndex;i++){

                shriekedArr[index++]=arrayElements[i];

        }
      
        arrayElements=shriekedArr;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int index = headIndex;
            @Override

            public boolean hasNext() {
                return index<=tailIndex;
            }

            @Override
            public E next() {
                return (E) arrayElements[index++];
            }
        };
    }
}
