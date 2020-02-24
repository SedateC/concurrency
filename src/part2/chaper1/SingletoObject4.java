package part2.chaper1;

public class SingletoObject4 {
    private static volatile SingletoObject4 instance; //volatile 保证构造函数全部做完

    private SingletoObject4() {
        //empty
    }

    private static class InstanceHolder{
        private final static SingletoObject4 instance = new SingletoObject4();
    }

    public static SingletoObject4 getInstance(){
        return InstanceHolder.instance;
    }
    //double check add volatile 不会空指针
  /*  public synchronized static SingletoObject4 getInstance(){ //串行化 慢
        if (instance == null){ //并行化
            synchronized (SingletoObject4.class){
                if (null == instance){
                    instance = new SingletoObject4();//懒加载 立即返回会
                }
            }
        }
        return SingletoObject4.instance;
    }*/
}
