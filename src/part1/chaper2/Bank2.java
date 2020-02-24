package part1.chaper2;

public class Bank2 {

    public static void main(String[] args) {
        /*
        * 可执行单元和Thread数据共享的实现
        * */
     TicketWindowsRunable TicketWindowsRunable = new TicketWindowsRunable();
     Thread t1 = new Thread(TicketWindowsRunable,"1号窗口");
     Thread t2 = new Thread(TicketWindowsRunable,"2号窗口");
     Thread t3 = new Thread(TicketWindowsRunable,"3号窗口");
        t1.start();
        t2.start();
        t3.start();

    }
}
