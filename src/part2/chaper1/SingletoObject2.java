package part2.chaper1;

public class SingletoObject2 {
    private static SingletoObject2 instance;

    private SingletoObject2() {
        //empty
    }
    //double check
    public synchronized static SingletoObject2 getInstance(){ //串行化 慢
        if (instance == null){ //并行化
            synchronized (SingletoObject2.class){
                if (null == instance){
                    instance = new SingletoObject2();//懒加载 立即返回会 引用会空指针
                }
            }
        }
        return SingletoObject2.instance;
    }
}
