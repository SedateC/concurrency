package chaper3;

public class CreateThread4 {
    public static int counter = 0;
    public static int index = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(
                null,new Runnable() {
                    @Override
                    public void run() {
                        try {
                            add(index);
                        }catch (Error e){
                            e.printStackTrace();
                            System.out.println(counter);
                        }
                    }
                    private void add(int i ){
                        counter++;
                        add(i+1);
                    }
                },"test",1<<24);
        //stackSize 调整JVM 虚拟内存的栈的值 代表线程占用的stack大小 如果没有传入

        t1.start();
    }


}
