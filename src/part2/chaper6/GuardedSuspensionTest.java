package part2.chaper6;

public class GuardedSuspensionTest {
    public static void main(String[] args) throws InterruptedException {
        RequestQueue requestQueue =  new RequestQueue();
        new ClientThread(requestQueue,"Alex").start();
        ServerThread serverThread =  new ServerThread(requestQueue);
        serverThread.start();
        Thread.sleep(10_000);
        serverThread.closed();

    }
}
