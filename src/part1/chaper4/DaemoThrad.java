package part1.chaper4;

public class DaemoThrad {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"运行开始");
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName()+"运行结束");
                }catch (Exception error){
                    error.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"运行结束");
                }
            }
        });
        t.setDaemon(true);
        //主线程结束后 守护线程消失
        t.start();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName());
    }
}
