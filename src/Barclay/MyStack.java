package Barclay;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by linyujie on 10/26/17.
 */

public class MyStack<T> implements MyStackInterface<T> {

    private class ListNode<T> {
        private T item;
        public ListNode<T> next;
        ListNode()
        {
            this.item = null;
            this.next = null;
        }
        ListNode(T item, ListNode<T> next) {
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return item == null && next == null;
        }
    }

    private ListNode<T> top;
    MyStack() {
        top = new ListNode<T>();
    }


    @Override
    public T pop() {
        if(this.isEmpty()) {
            throw new EmptyStackException();
        }
        T result = top.item;
        if(!top.end()) {
            top = top.next;
        }
        return result;

    }

    @Override
    public void push(T element) {
        top = new ListNode<T>(element, top);

    }

    @Override
    public boolean isEmpty() {
        return top.end();
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T result = top.item;
        return result;
    }
}
