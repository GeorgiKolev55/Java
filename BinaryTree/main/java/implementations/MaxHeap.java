package implementations;

import interfaces.Heap;
import org.apache.tools.ant.types.resources.comparators.Reverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;
    public MaxHeap(){
        elements=new ArrayList<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(E element) {
        elements.add(element);
        heapifyUp(this.size()-1);

    }

    private void heapifyUp(int i) {

        while (hasParent(i) && less(parent(i),i)){
            Collections.swap(this.elements,parent(i),i);
            i = parent(i);
        }
    }

    private boolean less(int parent,int i) {
        return elements.get(i).compareTo(elements.get(parent))>0;
    }


    private int parent(int i) {
        return  (i-1)/2;

    }

    private boolean hasParent(int i) {
        return i>0;
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }
        return this.elements.get(0);
    }
}
