package part2.chaper4;

public class ReadWriterLockClient {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData(10);
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new WriterWorker(shareData,"dalasdasdasd").start();
        new WriterWorker(shareData,"DALASDASDASD").start();
    }
}
