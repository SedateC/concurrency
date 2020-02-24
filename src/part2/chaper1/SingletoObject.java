package part2.chaper1;

public class SingletoObject {
    private static SingletoObject instance;

    private SingletoObject() {
        //empty
    }

    public synchronized static SingletoObject getInstance(){ //串行化 慢
        if (instance == null){
            instance = new SingletoObject();
        }
        return instance;
    }
}
