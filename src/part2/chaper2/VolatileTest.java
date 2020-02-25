package part2.chaper2;

public class VolatileTest {
    /*
    如果是写的时候直接从内存拿
    如果是读没有加volatile 则从 cache 中拿
    * 一旦一个共享变量被volatile 修饰，
    * 1，保证不同线程的可见性，但是不保证原子性 原子性赋值过程的可能释放资源给其他线程执行
    * 2，禁止对其进行重排序
    * 3，保证重排序代码的顺序准确
    * 4，强制对缓存的修改立即刷入主内存
    * 5，通知其他cache 失效
    *
    * volatile
    * 1，使用场景 doubleCheck 保证屏障前后一致性
    * 2，使用场景 多线程共享标记flag 保证标记不读缓存
    *
    * */

    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    public static void main(String[] args) {
        new Thread(()->{ //加volatile 多个线程也会出问题
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT){
                if (INIT_VALUE != localValue){
                    System.out.printf("the value update to %d \n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"t1").start();


        new Thread(()->{
            while (true){
                    INIT_VALUE++;
                    System.out.printf("the value add to %d \n",INIT_VALUE);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }


}
