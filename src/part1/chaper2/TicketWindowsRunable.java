package part1.chaper2;

public class  TicketWindowsRunable implements Runnable{
    private static  int index = 1;
    private static final int MAX  = 50;
    private String name;

    @Override
    public void run() {
        while ( index <= MAX){
            System.out.println("这是"+Thread.currentThread().getName()+"号柜台，当前号码是:"+index);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index ++;
        }
    }
}
