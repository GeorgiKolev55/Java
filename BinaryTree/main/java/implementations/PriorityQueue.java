package implementations;

import interfaces.AbstractQueue;

import java.util.*;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    List<E> queues;

    public PriorityQueue() {
        this.queues = new ArrayList<>();
    }

    @Override
    public int size() {
        return queues.size();
    }

    @Override
    public void add(E element) {
        queues.add(element);

    }

    @Override
    public E peek() {
       return  queues.get(0);
    }
     public void HeapifyDown(int index){
        while (index<queues.size()/2){
            int child = 2*index+1;
            if (child+1<this.queues.size()&& less(child,child+1)){
                child=child+1;
            }
           if( less(child,index)){
            break;
           }

           Collections.swap(queues,index,child);
           index=child;
        }
     }

    private boolean less(int e, int e1) {
        return queues.get(e).compareTo(queues.get(e1))<0;
    }

    @Override
    public E poll(){
        if (this.queues.size()==0){
            throw new IllegalStateException();
        }
        else  {
            E val = this.queues.get(0);
            Collections.swap(this.queues,0,this.queues.size()-1);
            HeapifyDown(0);
            return val;

        }


    }
}
