package part1.chaper11;

public class ThreadException {
    private final  static int A = 10;
    private final  static int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(5_000);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t.setUncaughtExceptionHandler((thread,e)->{
            System.out.println(thread);
            System.out.println(e);
        });
        t.start();
        new Test1().test();
    }
}
