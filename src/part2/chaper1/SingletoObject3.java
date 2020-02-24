package part2.chaper1;

public class SingletoObject3 {
    private static volatile SingletoObject3 instance; //volatile 保证构造函数全部做完

    private SingletoObject3() {
        //empty
    }
    //double check add volatile 不会空指针
    public synchronized static SingletoObject3 getInstance(){ //串行化 慢
        if (instance == null){ //并行化
            synchronized (SingletoObject3.class){
                if (null == instance){
                    instance = new SingletoObject3();//懒加载 立即返回会
                }
            }
        }
        return SingletoObject3.instance;
    }
}
