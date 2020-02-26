package part2.chaper7;

import java.util.HashMap;
import java.util.Map;


public class ThreadLocalSimulator<T> {
    private final Map<Thread,T> storage = new HashMap<>();

    public synchronized void set(T t){
        storage.put(Thread.currentThread(),t);
    }

    public synchronized T get(){
        T value = storage.get(Thread.currentThread());
        if (null == value) {
           return initialValue();
        }
        return value;
    }

    private T initialValue() {
        return null;
    }
}
