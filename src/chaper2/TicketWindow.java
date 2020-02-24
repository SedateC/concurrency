package chaper2;

public class TicketWindow extends Thread{
    private final String name;
    private final int max = 50;
    private static int index = 1;
    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while ( index <= max){
            System.out.println("这是"+name+"号，当前号码是:"+index);
            index ++;
        }
    }
}
