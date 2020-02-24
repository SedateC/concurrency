package part2.chaper1;

import java.util.stream.IntStream;

public class SingletoObject5 {
    private static volatile SingletoObject5 instance; //volatile 保证构造函数全部做完

    private SingletoObject5() {
        //empty
    }
    private enum Singleton{
        INSTANCE;
        private final SingletoObject5 instance;

        Singleton() {
            this.instance = new SingletoObject5();
        }

        public SingletoObject5 getInstance(){
            return instance;
        }
    }
    public static SingletoObject5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i->{
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    System.out.println(Singleton.INSTANCE.getInstance());
                }
            }.start();
        });
    }
}
