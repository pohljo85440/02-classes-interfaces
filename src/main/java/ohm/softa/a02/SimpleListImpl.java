package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {

    @Override
    public Iterator<Object> iterator() {
        return  new SimpleIteratorImpl();
    }

    private static class Element {
        Object item;
        Element next;

        public Element(Object item) {
            this.item = item;
        }
    }

    private class SimpleIteratorImpl implements Iterator<Object> {
        Element current;
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Object next() {
            current = current.next;
            return current.item;
        }

        public SimpleIteratorImpl() {
            current = new Element(null);
            current.next = head;
        }
    }

    Element head;

    @Override
    public void add(Object o) {
        Element newElement = new Element(o);

        if (head == null) {
            head = newElement;
            return;
        }
        newElement.next = head;
        head = newElement;
    }

    @Override
    public int size() {
        if(head == null) {
            return 0;
        }

        int size = 1;
        Element current = head;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        Element current = head;

        SimpleListImpl result = new SimpleListImpl();

        if(current == null) {
            return result;
        }

        do  {
            if(filter.include(current.item)) {
                result.add(current.item);
            }
            current = current.next;
        } while (current.next != null);

        return result;
    }
}
