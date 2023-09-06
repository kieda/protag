package io.hostilerobot.protag.util;

import java.util.*;

/**
 * mirrors a deque onto a list, such that additions on the deque will
 * modify the delegate list
 *
 * the deque is treated as the main handler for this list.
 *
 *     List<Node> children = new LinkedList<>();
 *     Deque<Node> items = new MirrorDeque<>(new LinkedList<>(), children);
 *
 *     items.add / remove / addAll / etc .. // will modify children and items
 *     children.add / remove / addAll / etc ... // will modify children, leave items intact
 */
public class MirrorDeque<E> implements Deque<E> {
    private final Deque<E> source;
    private final List<? super E> target;

    public MirrorDeque(Deque<E> source, List<? super E> target) {
        this.source = source;
        this.target = target;
    }

    private boolean targetOfferFirst(E item) {
        if(target instanceof Deque) {
            Deque<E> deq = (Deque<E>) target;
            return deq.offerFirst(item);
        } else {
            try {
                target.add(0, item);
                return true;
            } catch(IllegalStateException ex) {
                return false;
            }
        }
    }
    private void targetAddFirst(E item) {
        if(target instanceof Deque) {
            Deque<E> deq = (Deque<E>) target;
            deq.addFirst(item);
        } else {
            target.add(0, item);
        }
    }

    @Override
    public void addFirst(E e) {
        source.addFirst(e);
        // if source.addFirst fails, no need to roll back
        boolean success = false;
        try {
            targetAddFirst(e);
            // if we get to here without an error, we don't reset any state
            success = true;
        } finally {
            // otherwise rollback the source state
            if(!success)
                source.removeFirst();
        }
    }

    @Override
    public void addLast(E e) {
        // same idea as addFirst
        source.addLast(e);
        boolean success = false;

        try {
            target.add(e);
        } finally {
            if(!success)
                source.removeLast();
        }
    }

    @Override
    public boolean offerFirst(E e) {
        // offer the first item. If that is successful, try to add the second item
        // if the second item offer was not a success, then rollback the changes
        if(source.offerFirst(e)) {
            boolean success = false;
            try {
                success = targetOfferFirst(e);
            } finally {
                if(!success)
                    source.removeFirst();
            }
            return success;
        }
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        // same idea as offerFirst
        if(source.offerLast(e)) {
            boolean success = false;
            try {
                success = target.add(e);
            } finally {
                if(!success)
                    source.removeLast();
            }
            return success;
        }
        return false;
    }


    @Override
    public E getFirst() {
        return source.getFirst();
    }

    @Override
    public E getLast() {
        return source.getLast();
    }

    @Override
    public E peekFirst() {
        return source.peekFirst();
    }

    @Override
    public E peekLast() {
        return source.peekLast();
    }

    @Override
    public boolean offer(E e) {
        return offerFirst(e);
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public boolean contains(Object o) {
        return source.contains(o);
    }

    @Override
    public int size() {
        return source.size();
    }

    @Override
    public boolean isEmpty() {
        return source.isEmpty();
    }

    @Override
    public boolean add(E e) {
        return offerLast(e);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return source.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for(E item : c) {
            changed = add(item) || changed;
        }
        return changed;
    }

    @Override
    public Object[] toArray() {
        return source.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return source.toArray(a);
    }

    private Iterator<E> wrapIter(Iterator<E> in) {
        // wrap iterator so addition and removal is disallowed in the iterator
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return in.hasNext();
            }

            @Override
            public E next() {
                return in.next();
            }
        };
    }
    @Override
    public Iterator<E> iterator() {
        return wrapIter(source.iterator());
    }

    @Override
    public Iterator<E> descendingIterator() {
        return wrapIter(source.descendingIterator());
    }

    /* ==== Unsupported ====
     *
     * Offering removal on the source list would require us to do a bit more finesse
     * like having the source list have a list of nodes that we map to in the target, which can then be removed
     * by directly linking em.
     *
     * However we don't need this functionality for now, and only permitting adding elements to the front or back provides
     * us with the funtionality we need without making it too complex
     *
     * "You must ask yourself, is this this too much voodoo for our mission statement?"
     */

    @Override
    public E remove() {
        throw new UnsupportedOperationException("Unsupported: remove()");
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException("Unsupported: poll()");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported: clear()");
    }

    @Override
    public E pop() {
        throw new UnsupportedOperationException("Unsupported: pop()");
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Unsupported: removeFirst()");
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Unsupported: removeLast()");
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException("Unsupported: pollFirst()");
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException("Unsupported: pollLast()");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported: remove(Object o)");
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException("Unsupported: removeFirstOccurrence(Object o)");
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException("Unsupported: removeLastOccurrence(Object o)");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported: removeAll(Collection<?> c)");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported: retainAll(Collection<?> c)");
    }
}
