package part1.chaper2;

public class Bank {
    public static void main(String[] args){
        TicketWindow ticketWindow = new TicketWindow("柜台1");
        TicketWindow ticketWindow2 = new TicketWindow("柜台2");
        TicketWindow ticketWindow3 = new TicketWindow("柜台3");
        ticketWindow.start();
        ticketWindow2.start();
        ticketWindow3.start();
    }
}
