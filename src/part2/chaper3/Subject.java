package part2.chaper3;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserver(){

    }
}
