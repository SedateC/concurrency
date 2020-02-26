package part2.chaper5;

public class SyncInvoker {
    public static void main(String[] args) {

    }

    private static String get() throws InterruptedException {
        Thread.sleep(10_0000);
        return "finish";
    }
}
