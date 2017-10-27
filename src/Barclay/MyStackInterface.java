package Barclay;

/**
 * Created by linyujie on 10/26/17.
 */
public interface MyStackInterface<T> {
        public T pop();
        public void push(T element);
        public boolean isEmpty();
        public T peek();
}
