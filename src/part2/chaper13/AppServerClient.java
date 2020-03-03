package part2.chaper13;

import java.io.IOException;

public class AppServerClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer server = new AppServer(13345);
        server.start();
        Thread.sleep(15_000);
        System.out.println("已经过了15s");
        server.shutdown();
    }
}
